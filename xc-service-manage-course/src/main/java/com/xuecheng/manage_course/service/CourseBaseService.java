package com.xuecheng.manage_course.service;

import com.xuecheng.framework.domain.course.CourseBase;
import com.xuecheng.framework.model.response.QueryResponseResult;

/**
 * @author wangjun
 * @version 1.0
 * @date 2020/5/25 16:24
 */
public interface CourseBaseService {
    QueryResponseResult findCourseBasePage(int currentPage, int pageSize);

    CourseBase findOne(String id);

    CourseBase updateCourseBase(String id,CourseBase courseBase);

    CourseBase addCourseBase(CourseBase courseBase);
}
