package com.xuecheng.api.course;

import com.xuecheng.framework.domain.course.Teachplan;
import com.xuecheng.framework.domain.course.ext.TeachplanNode;
import com.xuecheng.framework.model.response.ResponseResult;
import io.swagger.annotations.Api;

/**
 * @author wangjun
 * @version 1.0
 * @date 2020/5/25 9:16
 */
@Api(description = "课程计划的增删改查")
public interface CourseTeachPlanControllerApi {

    public TeachplanNode findTeachPlanByCourseId(String courseId);
    public ResponseResult addTeachplan(Teachplan teachplan);
}
