package com.xuecheng.manage_course.dao;

import com.xuecheng.framework.domain.course.CourseMarket;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author wangjun
 * @version 1.0
 * @date 2020/5/26 15:25
 */
public interface CourseMarketRepository extends JpaRepository<CourseMarket,String> {
}
