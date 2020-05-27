package com.xuecheng.manage_cms.dao;

import com.xuecheng.framework.domain.system.SysDictionary;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author wangjun
 * @version 1.0
 * @date 2020/5/26 13:35
 */
public interface SysDicthinaryRepository extends MongoRepository<SysDictionary,String> {
    public SysDictionary findByDType(String dType);
}
