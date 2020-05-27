package com.xuecheng.manage_cms.service.impl;

import com.alibaba.fastjson.JSON;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.CmsTemplate;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.domain.cms.response.CmsCode;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.exception.ExceptionCast;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.QueryResult;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.manage_cms.config.CmsClientConfig;
import com.xuecheng.manage_cms.dao.CmsPageRepository;
import com.xuecheng.manage_cms.dao.CmsTemplateIdRepository;
import com.xuecheng.manage_cms.service.CmsPageService;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.commons.io.IOUtils;
import org.bson.types.ObjectId;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author wangjun
 * @version 1.0
 * @date 2020/5/7 22:51
 */
@Service
public class CmsPageServiceImpl implements CmsPageService {
    @Autowired
    private CmsPageRepository pageRepository;
    @Autowired
    private CmsTemplateIdRepository templateIdRepository;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private GridFsTemplate gridFsTemplate;
    @Autowired
    private GridFSBucket gridFSBucket;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Override
    public QueryResponseResult findPage(int page, int size, QueryPageRequest pageRequest) {
        if(page <= 1){
            page=0;
        }else {
            page=page-1;
        }
        if(size<=10){
            size=10;
        }
        PageRequest pageable = PageRequest.of(page, size);
        String siteId = pageRequest.getSiteId();
        String pageAliase = pageRequest.getPageAliase();
        String pageName = pageRequest.getPageName();
        String pageType = pageRequest.getPageType();

        CmsPage cmsPage = new CmsPage();
        if(!StringUtils.isEmpty(siteId)){
            cmsPage.setSiteId(siteId);
        }
        if(!StringUtils.isEmpty(pageType)){
            cmsPage.setPageType(pageType);
        }


        ExampleMatcher matcher = ExampleMatcher.matching();
        if(!StringUtils.isEmpty(pageAliase)){
            cmsPage.setPageAliase(pageAliase);
            matcher = matcher.withMatcher("pageAliase", ExampleMatcher.GenericPropertyMatchers.contains());
        }
        if(!StringUtils.isEmpty(pageName)){
            cmsPage.setPageName(pageName);
            matcher = matcher.withMatcher("pageName",ExampleMatcher.GenericPropertyMatchers.contains());
        }
        Example<CmsPage> example = Example.of(cmsPage, matcher);
        Page<CmsPage> all = pageRepository.findAll(example, pageable);
        long totalNum = all.getTotalElements();
        List<CmsPage> cmsPageList = all.getContent();
        QueryResult queryResult = new QueryResult();
        queryResult.setTotal(totalNum);
        queryResult.setList(cmsPageList);


        QueryResponseResult queryResponseResult = new QueryResponseResult(CommonCode.SUCCESS,queryResult);
        return queryResponseResult;

    }

    @Override
    public CmsPageResult addPage(CmsPage cmsPage) {
        //先判断页面是否存在
        CmsPage page = pageRepository.findByAndPageNameAndPageWebPathAndSiteId(cmsPage.getPageName(),
                cmsPage.getPageWebPath(), cmsPage.getSiteId());
        if(page != null){
            ExceptionCast.cast(CmsCode.CMS_ADDPAGE_EXISTSNAME);
        }
        cmsPage.setPageId(null);
        cmsPage = pageRepository.save(cmsPage);
        return new CmsPageResult(CommonCode.SUCCESS,cmsPage);
    }

    @Override
    public CmsPageResult updatePage(String id, CmsPage cmsPage) {
        CmsPage page = this.findById(id);
        if(page != null){
            page.setTemplateId(cmsPage.getTemplateId());
            // 更新所属站点
            page.setSiteId(cmsPage.getSiteId());
            // 更新页面别名
            page.setPageAliase(cmsPage.getPageAliase());
            // 更新页面名称
            page.setPageName(cmsPage.getPageName());
            // 更新访问路径
            page.setPageWebPath(cmsPage.getPageWebPath());
            // 更新物理路径
            page.setPagePhysicalPath(cmsPage.getPagePhysicalPath());

            CmsPage save = pageRepository.save(page);
            if(save != null){
                return new CmsPageResult(CommonCode.SUCCESS,save);
            }
        }
        return new CmsPageResult(CommonCode.FAIL,null);
    }

    @Override
    public CmsPage findById(String id) {
        Optional<CmsPage> optional = pageRepository.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }else {
            return null;
        }
    }

    @Override
    public CmsPageResult deletePage(String id) {
        Optional<CmsPage> optional = pageRepository.findById(id);
        if(optional.isPresent()){
            pageRepository.deleteById(id);
            return new CmsPageResult(CommonCode.SUCCESS,optional.get());
        }else {
            return new CmsPageResult(CommonCode.FAIL,null);
        }
    }

    // 调用静态方法
    public String getHtml(String pageId){
        String templateByPageId = getTemplateByPageId(pageId);
        if(StringUtils.isEmpty(templateByPageId)){
            ExceptionCast.cast(CmsCode.CMS_GENERATEHTML_TEMPLATEISNULL);
        }
        Map model = getModelByPageId(pageId);
        if(StringUtils.isEmpty(model)){
            ExceptionCast.cast(CmsCode.CMS_GENERATEHTML_DATAISNULL);
        }
        String html = generateHtml(model, templateByPageId);
        if(StringUtils.isEmpty(html)){
           ExceptionCast.cast(CmsCode.CMS_GENERATEHTML_HTMLISNULL);
       }
       return html;
    }
    //  静态化方法
    private String generateHtml(Map map,String content){
        try {
            Configuration configuration = new Configuration(Configuration.getVersion());
            StringTemplateLoader stringTemplateLoader = new StringTemplateLoader();
            stringTemplateLoader.putTemplate("template",content);
            configuration.setTemplateLoader(stringTemplateLoader);
            Template template = configuration.getTemplate("template");
            String s = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
            return s;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    // 获取模板文件
    private String getTemplateByPageId(String pageId) {
        CmsPage cmsPage = findById(pageId);
        if(StringUtils.isEmpty(cmsPage)){
            ExceptionCast.cast(CmsCode.CMS_GENERATEHTML_TEMPLATEISNULL);
        }
        String templateId = cmsPage.getTemplateId();
        if (StringUtils.isEmpty(templateId)){
            ExceptionCast.cast(CmsCode.CMS_GENERATEHTML_DATAURLISNULL);
        }
        Optional<CmsTemplate>templateOptional = templateIdRepository.findById(templateId);
        if(!templateOptional.isPresent()){
            ExceptionCast.cast(CmsCode.CMS_GENERATEHTML_DATAURLISNULL);
        }
        String templateFileId = templateOptional.get().getTemplateFileId();
        // 根据模板id获取文件
        GridFSFile gridFSFile = gridFsTemplate.findOne(new Query(Criteria.where("_id").is(templateFileId)));
        //打开下载流对象
        GridFSDownloadStream gridFSDownloadStream = gridFSBucket.openDownloadStream(gridFSFile.getObjectId());
        //创建gridFsResource，用于获取流对象
        GridFsResource gridFsResource = new GridFsResource(gridFSFile, gridFSDownloadStream);
        //获取流中的数据
        String content = null;
        try {
            content = IOUtils.toString(gridFsResource.getInputStream(), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }
    // 获取页面数据
    private Map getModelByPageId(String pageId){
        CmsPage cmsPage = findById(pageId);
        if(StringUtils.isEmpty(cmsPage)){
            ExceptionCast.cast(CmsCode.CMS_GENERATEHTML_TEMPLATEISNULL);
        }
        String dataUrl = cmsPage.getDataUrl();
        if(StringUtils.isEmpty(dataUrl)){
            ExceptionCast.cast(CmsCode.CMS_GENERATEHTML_DATAURLISNULL);
        }
        ResponseEntity<Map> forEntity = restTemplate.getForEntity(dataUrl, Map.class);
        Map body = forEntity.getBody();
        return body;
    }
    // 发布页面
    public ResponseResult postPage(String pageId){
        // 获取页面静态化文件
        String content = this.getHtml(pageId);
        // 把文件存储到GridFs
        this.saveHtmlToGridFs(pageId,content);
        // 发送消息
        this.sendMessage(pageId);
        return new ResponseResult(CommonCode.SUCCESS);
    }

    // 存储文件到GridFs
    private void saveHtmlToGridFs(String pageId,String content){
        // 判断页面是否存在
        CmsPage cmsPage = findById(pageId);
        if(cmsPage == null){
            ExceptionCast.cast(CmsCode.CMS_PAGE_NOT_EXIST);
        }
        try {
            InputStream inputStream = IOUtils.toInputStream(content, "utf-8");
            ObjectId objectId = gridFsTemplate.store(inputStream, cmsPage.getPageName());
            // 将id更新到页面
            cmsPage.setHtmlFileId(objectId.toHexString());
            pageRepository.save(cmsPage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void sendMessage(String pageId){
        CmsPage cmsPage = findById(pageId);
        if(cmsPage == null){
            ExceptionCast.cast(CmsCode.CMS_PAGE_NOT_EXIST);
        }
        HashMap<String, String> map = new HashMap<>();
        map.put("pageId",pageId);
        String message = JSON.toJSONString(map);
        rabbitTemplate.convertAndSend(CmsClientConfig.EX_ROUTING_CMS_POSTPAGE,
                cmsPage.getSiteId(),message);
    }
}
