package com.xuecheng.manage_cms.dao;

import com.xuecheng.framework.domain.cms.CmsPage;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author wangjun
 * @version 1.0
 * @date 2020/5/7 21:27
 */
public interface CmsPageRepository extends MongoRepository<CmsPage,String> {
        CmsPage findByAndPageNameAndPageWebPathAndSiteId(String pageName,String pageWebPath,String siteId);
}
