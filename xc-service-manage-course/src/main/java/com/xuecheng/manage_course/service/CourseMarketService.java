package com.xuecheng.manage_course.service;

import com.xuecheng.framework.domain.course.CourseMarket;

/**
 * @author wangjun
 * @version 1.0
 * @date 2020/5/26 15:24
 */
public interface CourseMarketService {
    CourseMarket updateCourseMarket(String courseid,CourseMarket courseMarket);

    CourseMarket findOneCourseMarket(String coursid);
}
