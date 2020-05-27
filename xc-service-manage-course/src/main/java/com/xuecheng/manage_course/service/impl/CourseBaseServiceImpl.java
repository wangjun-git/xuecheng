package com.xuecheng.manage_course.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xuecheng.framework.domain.course.CourseBase;
import com.xuecheng.framework.domain.course.ext.CourseInfo;
import com.xuecheng.framework.exception.ExceptionCast;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.QueryResult;
import com.xuecheng.manage_course.dao.CourseBaseMapper;
import com.xuecheng.manage_course.dao.CourseBaseRepository;
import com.xuecheng.manage_course.service.CourseBaseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author wangjun
 * @version 1.0
 * @date 2020/5/25 16:24
 */
@Service
public class CourseBaseServiceImpl implements CourseBaseService {
    @Autowired
    private CourseBaseMapper courseBaseMapper;
    @Autowired
    private CourseBaseRepository courseBaseRepository;
    @Override
    public QueryResponseResult findCourseBasePage(int currentPage, int pageSize) {
//        Page pageHelp = new Page<CourseInfo>(currentPage,pageSize);
        PageHelper.startPage(currentPage, pageSize);
        List<CourseInfo> list = courseBaseMapper.findAllCourseBase();
        PageInfo<CourseInfo> courseInfoPageInfo = new PageInfo<>(list);
        List<CourseInfo> courInfoList = courseInfoPageInfo.getList();
        long total = courseInfoPageInfo.getTotal();
        QueryResult<CourseInfo> queryResult = new QueryResult<>();
        queryResult.setList(courInfoList);
        queryResult.setTotal(total);
        return new QueryResponseResult(CommonCode.SUCCESS,queryResult);
    }

    @Override
    public CourseBase findOne(String id) {
        Optional<CourseBase> optional = courseBaseRepository.findById(id);
        if (!optional.isPresent()){
            ExceptionCast.cast(CommonCode.FAIL);
        }
        return optional.get();
    }

    @Override
    public CourseBase updateCourseBase(String id,CourseBase courseBase){
        Optional<CourseBase> optional = courseBaseRepository.findById(id);
        if(!optional.isPresent()){
            ExceptionCast.cast(CommonCode.FAIL);
        }
        CourseBase cb = optional.get();
        BeanUtils.copyProperties(courseBase,cb);
        CourseBase save = courseBaseRepository.save(cb);
        return save;
    }

    @Override
    public CourseBase addCourseBase(CourseBase courseBase) {
        CourseBase save = courseBaseRepository.save(courseBase);
        return save;
    }
}
