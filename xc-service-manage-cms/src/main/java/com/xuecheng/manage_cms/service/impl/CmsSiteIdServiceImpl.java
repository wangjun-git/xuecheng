package com.xuecheng.manage_cms.service.impl;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.CmsSite;
import com.xuecheng.framework.domain.cms.response.CmsSiteResult;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.QueryResult;
import com.xuecheng.manage_cms.dao.CmsSiteIdRepository;
import com.xuecheng.manage_cms.service.CmsSiteIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangjun
 * @version 1.0
 * @date 2020/5/9 22:40
 */
@Service
public class CmsSiteIdServiceImpl implements CmsSiteIdService {
    @Autowired
    private CmsSiteIdRepository siteIdRepository;
    @Override
    public CmsSiteResult findSiteIdList() {
        List<CmsSite> all = siteIdRepository.findAll();
       return new CmsSiteResult(CommonCode.SUCCESS,all);
    }
}
