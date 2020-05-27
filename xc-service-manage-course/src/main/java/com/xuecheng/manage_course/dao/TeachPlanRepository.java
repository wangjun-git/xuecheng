package com.xuecheng.manage_course.dao;

import com.xuecheng.framework.domain.course.Teachplan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author wangjun
 * @version 1.0
 * @date 2020/5/24 16:04
 */
public interface TeachPlanRepository extends JpaRepository<Teachplan,String> {
    public List<Teachplan> findByParentidAndCourseid(String parentId, String CourseId);
}

