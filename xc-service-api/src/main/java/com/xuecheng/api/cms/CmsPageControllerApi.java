package com.xuecheng.api.cms;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.ResponseResult;

/**
 * @author wangjun
 * @version 1.0
 * @date 2020/5/7 17:28
 */
public interface CmsPageControllerApi {
    public QueryResponseResult findPage(int currentPage, int rowPage, QueryPageRequest pageRequest);
    public CmsPageResult addPage(CmsPage cmsPage);
    public CmsPageResult updatePage(String id,CmsPage cmsPage);
    public CmsPage findById(String id);
    public CmsPageResult deletePage(String id);
    public ResponseResult postPage(String pageId);

}
