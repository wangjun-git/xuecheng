package com.xuecheng.manage_cms.dao;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.CmsSite;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author wangjun
 * @version 1.0
 * @date 2020/5/9 22:41
 */
public interface CmsSiteIdRepository extends MongoRepository<CmsSite,String> {
}
