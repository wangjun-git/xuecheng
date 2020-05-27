package com.xuecheng.manage_cms.dao;

import com.xuecheng.framework.domain.cms.CmsTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author wangjun
 * @version 1.0
 * @date 2020/5/10 18:43
 */
public interface CmsTemplateIdRepository extends MongoRepository<CmsTemplate,String> {
}
