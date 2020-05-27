package com.xuecheng.manage_course.controller;

import com.xuecheng.api.course.CourseTeachPlanControllerApi;
import com.xuecheng.framework.domain.course.Teachplan;
import com.xuecheng.framework.domain.course.ext.TeachplanNode;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.manage_course.service.CourseTeachPlanService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author wangjun
 * @version 1.0
 * @date 2020/5/25 9:21
 */
@RestController
@RequestMapping("/course/teachplan")
public class CourseTeachPlanController implements CourseTeachPlanControllerApi {

    @Autowired
    private CourseTeachPlanService courseTeachPlanService;

    @ApiOperation("查询课程计划")
    @GetMapping("list/{courseid}")
    @Override
    public TeachplanNode findTeachPlanByCourseId(@PathVariable("courseid") String courseId) {
        return courseTeachPlanService.findTeachPlanByCourseId(courseId);
    }

    @ApiOperation("添加课程计划")
    @PostMapping("/add")
    @Override
    public ResponseResult addTeachplan(@RequestBody Teachplan teachplan) {
       Teachplan teachplan1 = courseTeachPlanService.addTeachplan(teachplan);
       if(teachplan1 == null){
           return new ResponseResult(CommonCode.FAIL);
       }
       return new ResponseResult(CommonCode.SUCCESS);
    }
}
