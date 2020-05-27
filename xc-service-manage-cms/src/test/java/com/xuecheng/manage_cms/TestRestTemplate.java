package com.xuecheng.manage_cms;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @author wangjun
 * @version 1.0
 * @date 2020/5/13 10:36
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestRestTemplate {
    @Autowired
    private RestTemplate restTemplate;
    @Test
    public void test1(){

    }
}
