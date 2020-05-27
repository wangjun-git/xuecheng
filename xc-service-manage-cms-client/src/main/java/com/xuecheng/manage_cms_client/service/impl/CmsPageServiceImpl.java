package com.xuecheng.manage_cms_client.service.impl;

import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.CmsSite;
import com.xuecheng.framework.domain.cms.response.CmsCode;
import com.xuecheng.framework.exception.ExceptionCast;
import com.xuecheng.manage_cms_client.dao.CmsPageRepository;
import com.xuecheng.manage_cms_client.dao.CmsSiteIdRepository;
import com.xuecheng.manage_cms_client.service.CmsPageService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

/**
 * @author wangjun
 * @version 1.0
 * @date 2020/5/21 16:01
 */
@Service
public class CmsPageServiceImpl implements CmsPageService {
    @Autowired
    private CmsPageRepository pageRepository;
    @Autowired
    private CmsSiteIdRepository siteIdRepository;
    @Autowired
    private GridFsTemplate gridFsTemplate;
    @Autowired
    private GridFSBucket gridFSBucket;

    //部署文件到服务器
    public void deployHtml(String pageId){
        Optional<CmsPage> optional = pageRepository.findById(pageId);
        if (!optional.isPresent()){
            ExceptionCast.cast(CmsCode.CMS_PAGE_NOT_EXIST);
        }
        CmsPage page = optional.get();
        InputStream inputStream = this.getInputStream(page);
        if (StringUtils.isEmpty(inputStream)){
            ExceptionCast.cast(CmsCode.CMS_GENERATEHTML_HTMLISNULL);
        }
        String fileUrl = this.getFileUrl(page);
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(new File(fileUrl));
            IOUtils.copy(inputStream,fileOutputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (fileOutputStream != null){
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    // 获取文件路径
    private String getFileUrl(CmsPage page) {
//        Optional<CmsPage> optional = pageRepository.findById(pageId);
//        if (!optional.isPresent()){
//            ExceptionCast.cast(CmsCode.CMS_PAGE_NOT_EXIST);
//        }
        String pageWebPath = page.getPageWebPath();
        String siteId = page.getSiteId();
        Optional<CmsSite> op = siteIdRepository.findById(siteId);
        if (!op.isPresent()) {
            ExceptionCast.cast(CmsCode.CMS_SITE_NOT_EXIST);
        }
        String siteWebPath = op.get().getSiteWebPath();
        // 文件的物理路径
        return siteWebPath + pageWebPath + page.getPageName();
    }

    // 获取一个输入流
    private InputStream getInputStream(CmsPage page) {
        try {
            String htmlFileId = page.getHtmlFileId();
            GridFSFile gridFSFile = gridFsTemplate.findOne(new Query(Criteria.where("_id").is(htmlFileId)));
            GridFSDownloadStream gridFSDownloadStream = gridFSBucket.openDownloadStream(gridFSFile.getObjectId());
            GridFsResource gridFsResource = new GridFsResource(gridFSFile, gridFSDownloadStream);
            InputStream inputStream = gridFsResource.getInputStream();
            return inputStream;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
