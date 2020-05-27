package com.xuecheng.manage_course.dao;

import com.xuecheng.framework.domain.course.ext.CourseInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author wangjun
 * @version 1.0
 * @date 2020/5/25 16:26
 */
@Mapper
public interface CourseBaseMapper {

    public List<CourseInfo> findAllCourseBase();

}
