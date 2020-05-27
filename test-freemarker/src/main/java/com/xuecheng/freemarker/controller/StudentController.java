package com.xuecheng.freemarker.controller;

import com.xuecheng.freemarker.entity.Student;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wangjun
 * @version 1.0
 * @date 2020/5/14 14:25
 */
@Controller
@RequestMapping("/student")
public class StudentController {
    @RequestMapping("/test1")
    public String test1(Map<String,Object> map){
        Student student1 = new Student("乔峰", 12, "丐帮", new Date());
        Student student2 = new Student("虚竹", 12, "逍遥", new Date());
        Student student3 = new Student("段誉", 12, "大理", new Date());
        ArrayList<Student> students = new ArrayList<>();
        students.add(student1);
        students.add(student2);
        students.add(student3);
        map.put("students",students);
        return "test";
    }
}
