package com.xuecheng.freemarker.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


/**
 * @author wangjun
 * @version 1.0
 * @date 2020/5/14 14:21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private String name;
    private int age;
    private String address;
    private Date birth;
}
