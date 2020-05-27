package com.xuecheng.manage_cms.service.impl;

import com.xuecheng.framework.domain.cms.CmsTemplate;
import com.xuecheng.framework.domain.cms.response.CmsTemplateResult;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.manage_cms.dao.CmsTemplateIdRepository;
import com.xuecheng.manage_cms.service.CmsTemplateIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wangjun
 * @version 1.0
 * @date 2020/5/10 18:42
 */
@Service
public class CmsTemplateIdServiceImpl implements CmsTemplateIdService {
    @Autowired
    private CmsTemplateIdRepository templateIdRepository;
    @Override
    public CmsTemplateResult findTemplateId() {
        List<CmsTemplate> all = templateIdRepository.findAll();
        return new CmsTemplateResult(CommonCode.SUCCESS,all);
    }
}
