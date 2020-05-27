package com.xuecheng.manage_course.dao;

import com.xuecheng.framework.domain.course.ext.CategoryNode;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author wangjun
 * @version 1.0
 * @date 2020/5/26 10:26
 */
@Mapper
public interface CategroyMapper {
    CategoryNode findListCategory();
}
