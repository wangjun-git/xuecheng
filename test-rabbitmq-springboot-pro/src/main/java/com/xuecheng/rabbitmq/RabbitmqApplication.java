package com.xuecheng.rabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author wangjun
 * @version 1.0
 * @date 2020/5/20 15:23
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.xuecheng.rabbitmq.config")
public class RabbitmqApplication {
    public static void main(String[] args) {
        SpringApplication.run(RabbitmqApplication.class,args);
    }
}
