package com.xuecheng.manage_cms.service.impl;

import com.xuecheng.framework.domain.system.SysDictionary;
import com.xuecheng.manage_cms.dao.SysDicthinaryRepository;
import com.xuecheng.manage_cms.service.SysDicthinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wangjun
 * @version 1.0
 * @date 2020/5/26 13:34
 */
@Service
public class SysDicthinaryImpl implements SysDicthinaryService {
    @Autowired
    private SysDicthinaryRepository sysDicthinaryRepository;
    @Override
    public SysDictionary getByType(String type) {
       return sysDicthinaryRepository.findByDType(type);
    }
}
