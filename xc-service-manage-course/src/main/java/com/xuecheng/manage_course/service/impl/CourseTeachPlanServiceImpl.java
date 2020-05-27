package com.xuecheng.manage_course.service.impl;

import com.xuecheng.framework.domain.course.Teachplan;
import com.xuecheng.framework.domain.course.ext.TeachplanNode;
import com.xuecheng.manage_course.dao.TeachPlanMapper;
import com.xuecheng.manage_course.dao.TeachPlanRepository;
import com.xuecheng.manage_course.service.CourseTeachPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author wangjun
 * @version 1.0
 * @date 2020/5/25 9:27
 */
@Service
public class CourseTeachPlanServiceImpl implements CourseTeachPlanService {
    @Autowired
    private TeachPlanMapper teachPlanMapper;
    @Autowired
    private TeachPlanRepository teachPlanRepository;
    @Override
    public TeachplanNode findTeachPlanByCourseId(String courseId) {
        return teachPlanMapper.findTeachPlanByCourseId(courseId);
    }

    @Override
    public Teachplan addTeachplan(Teachplan teachplan) {
        teachplan.setGrade("3");
        if(StringUtils.isEmpty(teachplan.getParentid())){
            String courseid = teachplan.getCourseid();
            //查找上级节点的id
            List<Teachplan> list = teachPlanRepository.findByParentidAndCourseid("0", courseid);
            Teachplan teachplan1 = list.get(0);
            String id = teachplan1.getId();
            teachplan.setParentid(id);
            teachplan.setGrade("2");//如果能进if方法说明是二级节点
        }
        Teachplan save = teachPlanRepository.save(teachplan);
        return save;

    }
}
