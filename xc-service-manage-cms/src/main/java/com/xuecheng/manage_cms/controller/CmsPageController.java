package com.xuecheng.manage_cms.controller;

import com.xuecheng.api.cms.CmsPageControllerApi;
import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.manage_cms.service.CmsPageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author wangjun
 * @version 1.0
 * @date 2020/5/7 17:55
 */
@RestController
@RequestMapping("/cms/page")
@Api(value = "cms页面管理接口",description = "页面管理接口,提供增删改查")
public class CmsPageController implements CmsPageControllerApi {
    @Autowired
    private CmsPageService cmsPageService;
    @GetMapping("/list/{page}/{size}")
    @ApiOperation("分页查询页面列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value = "当前页数",required = true,paramType = "path",dataType = "int"),
            @ApiImplicitParam(name = "size",value = "每页数量",required = true,paramType = "path",dataType = "int")
    })
    @Override
    public QueryResponseResult findPage(@PathVariable("page") int page,
                                        @PathVariable("size") int size,
                                        QueryPageRequest  pageRequest) {

       return cmsPageService.findPage(page,size,pageRequest);

    }

    @ApiOperation("添加页面")
    @PostMapping("/add")
    @Override
    public CmsPageResult addPage(@RequestBody CmsPage cmsPage) {
        return cmsPageService.addPage(cmsPage);
    }

    @ApiOperation("修改页面")
    @PutMapping("/update/{id}")
    @ApiImplicitParam(name = "id",value = "页面ID",required = true,paramType = "path",dataType = "String")
    @Override
    public CmsPageResult updatePage(@PathVariable("id") String id, @RequestBody CmsPage cmsPage) {
        return cmsPageService.updatePage(id,cmsPage);
    }

    @ApiOperation("根据id查询页面")
    @GetMapping("/find/{id}")
    @Override
    public CmsPage findById(@PathVariable("id") String id) {
        return cmsPageService.findById(id);
    }

    @ApiOperation("删除页面")
    @DeleteMapping("/delete/{id}")
    @Override
    public CmsPageResult deletePage(@PathVariable("id") String id) {
        return cmsPageService.deletePage(id);
    }

    @ApiOperation("发布页面")
    @PostMapping("/postpage/{pageId}")
    @Override
    public ResponseResult postPage(@PathVariable("pageId") String pageId) {
        return cmsPageService.postPage( pageId);
    }
}
