package com.xuecheng.manage_cms.service.impl;

import com.xuecheng.framework.domain.cms.CmsConfig;
import com.xuecheng.manage_cms.dao.CmsConfigRepository;
import com.xuecheng.manage_cms.service.CmsConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

/**
 * @author wangjun
 * @version 1.0
 * @date 2020/5/13 10:19
 */
@Service
public class CmsConfigServiceImpl implements CmsConfigService {
    @Autowired
    private CmsConfigRepository configRepository;
    @Override
    public CmsConfig getConfigById(String id) {
        if(!StringUtils.isEmpty(id)){
            Optional<CmsConfig> optional = configRepository.findById(id);
            if(optional.isPresent()){
                return optional.get();
            }
        }
        return null;
    }
}
