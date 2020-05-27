package com.xuecheng.manage_cms.controller;

import com.xuecheng.api.cms.CmsSiteIdControllerApi;
import com.xuecheng.framework.domain.cms.response.CmsSiteResult;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.manage_cms.service.CmsSiteIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author wangjun
 * @version 1.0
 * @date 2020/5/9 22:38
 */
@RestController
@RequestMapping("/cms/page")
public class CmsSiteIdController implements CmsSiteIdControllerApi {
    @Autowired
    private CmsSiteIdService siteIdService;
    @GetMapping("/findSiteIdList")
    @Override
    public CmsSiteResult findSiteIdList() {
        return siteIdService.findSiteIdList();
    }
}
