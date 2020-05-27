package com.xuecheng.manage_cms_client.mq;

import com.alibaba.fastjson.JSON;
import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.response.CmsCode;
import com.xuecheng.framework.exception.ExceptionCast;
import com.xuecheng.manage_cms_client.dao.CmsPageRepository;
import com.xuecheng.manage_cms_client.service.CmsPageService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

/**
 * @author wangjun
 * @version 1.0
 * @date 2020/5/21 17:32
 */
@Component
public class CustomerPostPage {

    @Autowired
    private CmsPageRepository pageRepository;
    @Autowired
    private CmsPageService pageService;
    @RabbitListener(queues = {"${xuecheng.mq.queue}"})
    public void postPage(String message){
        Map map = JSON.parseObject(message, Map.class);
        String pageId = (String)map.get("pageId");
        Optional<CmsPage> byId = pageRepository.findById(pageId);
        if(!byId.isPresent()){
            ExceptionCast.cast(CmsCode.CMS_PAGE_NOT_EXIST);
        }
        // 发布页面
        pageService.deployHtml(pageId);
    }
}
