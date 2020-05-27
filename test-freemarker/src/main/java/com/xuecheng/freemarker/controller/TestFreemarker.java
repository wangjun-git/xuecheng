package com.xuecheng.freemarker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @author wangjun
 * @version 1.0
 * @date 2020/5/13 13:53
 */
@Controller
@RequestMapping("/freemarker")
public class TestFreemarker {
    @Autowired
    private RestTemplate restTemplate;
    @RequestMapping("/banner")
    public String index_banner(Map<String, Object> map){
//        ResponseEntity<Map> forEntity = restTemplate.getForEntity("http://localhost:31001/cms/config/get/5a791725dd573c3574ee333f", Map.class);
//        Map<String,Object> body = forEntity.getBody();
//        map.putAll(body);
        return "index_banner";
    }
}
