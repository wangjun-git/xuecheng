package com.xuecheng.api.course;

import com.xuecheng.framework.domain.course.CourseBase;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.ResponseResult;
import io.swagger.annotations.Api;

/**
 * @author wangjun
 * @version 1.0
 * @date 2020/5/25 16:01
 */
@Api(description = "我的课程总览的增删改查")
public interface CourseBaseControllerApi {
   QueryResponseResult findCourseBasePage(int currentPage,int pageSize);
   CourseBase findCourseBase(String id);
   ResponseResult updateCourseBase(String id,CourseBase courseBase);
   ResponseResult addCourseBase(CourseBase courseBase);
}
