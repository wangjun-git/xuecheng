package com.xuecheng.manage_course.service.impl;

import com.xuecheng.framework.domain.course.CourseMarket;
import com.xuecheng.manage_course.dao.CourseMarketRepository;
import com.xuecheng.manage_course.service.CourseMarketService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author wangjun
 * @version 1.0
 * @date 2020/5/26 15:24
 */
@Service
public class CourseMarketServiceImpl implements CourseMarketService {
    @Autowired
    private CourseMarketRepository courseMarketRepository;
    @Override
    public CourseMarket updateCourseMarket(String courseid,CourseMarket courseMarket) {
        Optional<CourseMarket> optinal = courseMarketRepository.findById(courseid);
        if(optinal.isPresent()){
            CourseMarket cm = optinal.get();
            BeanUtils.copyProperties(courseMarket,cm);
            CourseMarket save = courseMarketRepository.save(cm);
            return save;
        }
        courseMarket.setId(courseid);
        CourseMarket save = courseMarketRepository.save(courseMarket);
        return save;
    }

    @Override
    public CourseMarket findOneCourseMarket(String coursid) {
        Optional<CourseMarket> optional = courseMarketRepository.findById(coursid);
        if(!optional.isPresent()){
            return new CourseMarket();
        }
        return optional.get();
    }
}
