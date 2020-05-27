package com.xuecheng.manage_course.service;

import com.xuecheng.framework.domain.course.Teachplan;
import com.xuecheng.framework.domain.course.ext.TeachplanNode;

/**
 * @author wangjun
 * @version 1.0
 * @date 2020/5/25 9:27
 */
public interface CourseTeachPlanService {
    TeachplanNode findTeachPlanByCourseId(String courseId);

    Teachplan addTeachplan(Teachplan teachplan);
}
