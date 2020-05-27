package com.xuecheng.api.course;

import com.xuecheng.framework.domain.course.CourseMarket;
import com.xuecheng.framework.model.response.ResponseResult;
import io.swagger.annotations.Api;

/**
 * @author wangjun
 * @version 1.0
 * @date 2020/5/26 15:19
 */
@Api(description = "修改课程的营销信息")
public interface CourseMarketControllerApi {
    ResponseResult updateCourseMarket(String coursid, CourseMarket courseMarket);
    CourseMarket findOneCourseMarket(String coursid);
}
