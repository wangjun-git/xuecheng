package com.xuecheng.controller;

import com.xuecheng.entity.Student;
import com.xuecheng.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author wangjun
 * @version 1.0
 * @date 2020/5/14 15:34
 */
@Controller
@RequestMapping("demo")
public class ThymeleafTest {

    @RequestMapping("/user")
    public String test1(Model model){
//        User user = new User();
//        user.setAge(21);
//        user.setName("Jackson");
//        user.setBirth(new Date());
//        model.addAttribute("user",user);
        Student student1 = new Student("乔峰", 12, "丐帮", new Date());
        Student student2 = new Student("虚竹", 12, "逍遥", new Date());
        Student student3 = new Student("段誉", 12, "大理", new Date());
        ArrayList<Student> students = new ArrayList<>();
        students.add(student1);
        students.add(student2);
        students.add(student3);
        model.addAttribute("students",students);
        return "test";
    }
}
