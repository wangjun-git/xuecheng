package com.xuecheng.manage_course.dao;

import com.xuecheng.framework.domain.course.ext.TeachplanNode;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author wangjun
 * @version 1.0
 * @date 2020/5/25 9:41
 */
@Mapper
public interface TeachPlanMapper {
    TeachplanNode findTeachPlanByCourseId(String courseId);
}
