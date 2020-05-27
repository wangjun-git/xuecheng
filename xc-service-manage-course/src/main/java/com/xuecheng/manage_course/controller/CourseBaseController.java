package com.xuecheng.manage_course.controller;

import com.xuecheng.api.course.CourseBaseControllerApi;
import com.xuecheng.framework.domain.course.CourseBase;
import com.xuecheng.framework.exception.ExceptionCast;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.manage_course.service.CourseBaseService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author wangjun
 * @version 1.0
 * @date 2020/5/25 16:22
 */
@RestController
@RequestMapping("/course/coursebase")
public class CourseBaseController implements CourseBaseControllerApi {
    @Autowired
    private CourseBaseService courseBaseService;


    @ApiOperation("分页查询")
    @GetMapping("list/{page}/{size}")
    @Override
    public QueryResponseResult findCourseBasePage(@PathVariable("page") int currentPage,
                                                  @PathVariable("size") int pageSize) {
        return courseBaseService.findCourseBasePage(currentPage,pageSize);
    }

    @ApiOperation("查询课程基本信息")
    @GetMapping("find/{id}")
    @Override
    public CourseBase findCourseBase(@PathVariable("id") String id) {
        CourseBase courseBase = courseBaseService.findOne(id);
        if(courseBase == null){
            ExceptionCast.cast(CommonCode.FAIL);
        }
        return courseBase;
    }
    @ApiOperation("修改课程基本信息")
    @PostMapping("update/{id}")
    public ResponseResult updateCourseBase(@PathVariable("id") String id,@RequestBody CourseBase courseBase){
        CourseBase courseBase1 = courseBaseService.updateCourseBase(id, courseBase);
        if(courseBase1 != null){
            return new ResponseResult(CommonCode.SUCCESS);
        }
        return new ResponseResult(CommonCode.FAIL);
    }

    @ApiOperation("新增课程基本信息")
    @PostMapping("add")
    @Override
    public ResponseResult addCourseBase(@RequestBody CourseBase courseBase) {
        CourseBase cb = courseBaseService.addCourseBase(courseBase);
        if(cb == null){
            return new ResponseResult(CommonCode.FAIL);
        }
        return new ResponseResult(CommonCode.SUCCESS);
    }

}
