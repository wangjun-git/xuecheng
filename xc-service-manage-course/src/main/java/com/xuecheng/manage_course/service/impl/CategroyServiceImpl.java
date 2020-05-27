package com.xuecheng.manage_course.service.impl;

import com.xuecheng.framework.domain.course.ext.CategoryNode;
import com.xuecheng.manage_course.dao.CategroyMapper;
import com.xuecheng.manage_course.service.CategroyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wangjun
 * @version 1.0
 * @date 2020/5/26 10:23
 */
@Service
public class CategroyServiceImpl implements CategroyService {
    @Autowired
    private CategroyMapper categroyMapper;
    @Override
    public CategoryNode findListCategory() {
        return categroyMapper.findListCategory();
    }
}
