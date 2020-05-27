package com.xuecheng.api.course;

import com.xuecheng.framework.domain.course.ext.CategoryNode;
import io.swagger.annotations.Api;

/**
 * @author wangjun
 * @version 1.0
 * @date 2020/5/26 10:46
 */
@Api("课程分类")
public interface CategoryControllerApi {
    CategoryNode findListCategory();

}
