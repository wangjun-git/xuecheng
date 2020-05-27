package com.xuecheng.manage_cms.controller;

import com.xuecheng.framework.domain.cms.response.CmsCode;
import com.xuecheng.framework.exception.ExceptionCast;
import com.xuecheng.framework.web.BaseController;
import com.xuecheng.manage_cms.service.CmsPageService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;

import java.io.IOException;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author wangjun
 * @version 1.0
 * @date 2020/5/15 22:35
 */
@Api(description = "页面预览")
@Controller
public class CmsPreviewPageController extends BaseController {

    @Autowired
    private CmsPageService cmsPageService;

    @RequestMapping(value = "/cms/preview/{pageId}",method = GET)
    public void getHtml(@PathVariable String pageId){
        String html = cmsPageService.getHtml(pageId);
        if(StringUtils.isEmpty(html)){
            ExceptionCast.cast(CmsCode.CMS_GENERATEHTML_TEMPLATEISNULL);
        }
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(html.getBytes("utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }


        }

    }


