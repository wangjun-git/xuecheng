package com.xuecheng.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author wangjun
 * @version 1.0
 * @date 2020/5/14 15:37
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    String name;
    int age;
    Date birth;
}