package com.xuecheng.manage_cms;
import com.alibaba.fastjson.JSON;
import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.manage_cms.dao.CmsPageRepository;
import com.xuecheng.manage_cms.service.CmsPageService;
import com.xuecheng.manage_cms.service.impl.CmsPageServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author wangjun
 * @version 1.0
 * @date 2020/5/7 21:24
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestMongodb {

    @Autowired
    private CmsPageServiceImpl pageService;

    @Test
    public void test1(){

        String html = pageService.getHtml("5a795ac7dd573c04508f3a56");
        System.out.println(html);

    }
}
