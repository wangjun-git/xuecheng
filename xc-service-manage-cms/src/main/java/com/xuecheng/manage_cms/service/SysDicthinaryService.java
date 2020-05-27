package com.xuecheng.manage_cms.service;

import com.xuecheng.framework.domain.system.SysDictionary;

/**
 * @author wangjun
 * @version 1.0
 * @date 2020/5/26 13:34
 */
public interface SysDicthinaryService {
    SysDictionary getByType(String type);
}
