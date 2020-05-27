package com.xuecheng.manage_cms.service;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.ResponseResult;

/**
 * @author wangjun
 * @version 1.0
 * @date 2020/5/7 22:50
 */
public interface CmsPageService {
    QueryResponseResult findPage(int page, int size, QueryPageRequest pageRequest);

    CmsPageResult addPage(CmsPage cmsPage);

    CmsPageResult updatePage(String id, CmsPage cmsPage);

    CmsPage findById(String id);

    CmsPageResult deletePage(String id);

    String getHtml(String pageId);
    ResponseResult postPage(String pageId);
}
