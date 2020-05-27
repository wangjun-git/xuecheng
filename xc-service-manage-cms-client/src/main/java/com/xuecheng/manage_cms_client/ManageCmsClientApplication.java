package com.xuecheng.manage_cms_client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author wangjun
 * @version 1.0
 * @date 2020/5/21 10:20
 */
@SpringBootApplication
@ComponentScan("com.xuecheng.framework.domain.cms")
@ComponentScan("com.xuecheng.framework")
@ComponentScan()
public class ManageCmsClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(ManageCmsClientApplication.class,args);
    }
}
