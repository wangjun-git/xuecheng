package com.xuecheng.manage_course.dao;

import com.alibaba.fastjson.JSON;
import com.xuecheng.framework.domain.course.CourseBase;
import com.xuecheng.framework.domain.course.Teachplan;
import com.xuecheng.framework.domain.course.ext.TeachplanNode;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.manage_course.service.CourseBaseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

/**
 * @author Administrator
 * @version 1.0
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestDao {
    @Autowired
    CourseBaseMapper courseBaseMapper;
    @Autowired
    CourseBaseRepository courseBaseRepository;
    @Autowired
    TeachPlanRepository teachPlanRepository;
    @Autowired
    TeachPlanMapper teachPlanMapper;
    @Autowired
    CourseBaseService courseBaseService;
    @Test
    public void testCourseBaseRepository(){
        Optional<CourseBase> optional = courseBaseRepository.findById("402885816240d276016240f7e5000002");
        if(optional.isPresent()){
            CourseBase courseBase = optional.get();
//            System.out.println(courseBase);
        }
        List<Teachplan> teachplan = teachPlanRepository.findByParentidAndCourseid("0", "402881e66420279801642040a4b50004");
        System.out.println(JSON.toJSONString(teachplan));

    }

    @Test
    public void testCourseMapper(){
//        List<CourseInfo> allCourseBase = courseBaseMapper.findAllCourseBase();
//        System.out.println(JSON.toJSONString(allCourseBase));
        QueryResponseResult courseBasePage = courseBaseService.findCourseBasePage(1, 3);
        List list = courseBasePage.getQueryResult().getList();
        System.out.println(JSON.toJSONString(list));

    }
    @Test
    public void testTeachPlanMapper(){
        TeachplanNode teachPlan = teachPlanMapper.findTeachPlanByCourseId("4028e581617f945f01617f9dabc40000");
        System.out.println(JSON.toJSONString(teachPlan));
    }

    @Test
    public void test1(){
        Teachplan teachplan = new Teachplan();
        teachplan.setGrade("2");
        teachplan.setCourseid("4028e581617f945f01617f9dabc40000");
        Teachplan save = teachPlanRepository.save(teachplan);
        System.out.println(save);
    }
}
