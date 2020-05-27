package com.xuecheng.manage_cms.dao;

import com.xuecheng.framework.domain.cms.CmsConfig;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author wangjun
 * @version 1.0
 * @date 2020/5/13 10:19
 */
public interface CmsConfigRepository extends MongoRepository<CmsConfig,String> {
}
