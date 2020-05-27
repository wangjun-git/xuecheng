package com.xuecheng.manage_cms.controller;

import com.xuecheng.api.cms.CmstemplateIdControllerApi;
import com.xuecheng.framework.domain.cms.response.CmsTemplateResult;
import com.xuecheng.manage_cms.service.CmsTemplateIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangjun
 * @version 1.0
 * @date 2020/5/10 18:40
 */
@RestController
@RequestMapping("/cms/page")
public class CmsTemplateIdController implements CmstemplateIdControllerApi {

    @Autowired
    private CmsTemplateIdService templateIdService;
    @GetMapping("/findTemplateId")
    @Override
    public CmsTemplateResult findTemplateId() {
        return templateIdService.findTemplateId();
    }
}
