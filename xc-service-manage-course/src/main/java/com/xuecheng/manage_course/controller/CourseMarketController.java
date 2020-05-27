package com.xuecheng.manage_course.controller;

import com.xuecheng.api.course.CourseMarketControllerApi;
import com.xuecheng.framework.domain.course.CourseMarket;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.manage_course.service.CourseMarketService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author wangjun
 * @version 1.0
 * @date 2020/5/26 15:22
 */
@RestController
@RequestMapping("/course/courseMarket")
public class CourseMarketController implements CourseMarketControllerApi {
    @Autowired
    private CourseMarketService courseMarketService;

    @ApiOperation("修改或添加课程的营销信息")
    @PostMapping("update/{courseid}")
    @Override
    public ResponseResult updateCourseMarket(@PathVariable("courseid") String coursid,@RequestBody CourseMarket courseMarket) {
        CourseMarket courseMarket1 =courseMarketService.updateCourseMarket(coursid,courseMarket);
        if(courseMarket1 != null){
            return new ResponseResult(CommonCode.SUCCESS);
        }
        return new ResponseResult(CommonCode.FAIL);
    }

    @ApiOperation("获取课程营销信息")
    @GetMapping("get/{courseid}")
    @Override
    public CourseMarket findOneCourseMarket(@PathVariable("courseid") String coursid) {
        CourseMarket courseMarket = courseMarketService.findOneCourseMarket(coursid);
        return courseMarket;
    }
}
