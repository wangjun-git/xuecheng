package com.xuecheng.rabbitmq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author wangjun
 * @version 1.0
 * @date 2020/5/20 17:44
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ConstumerTest {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Test
    public void test1(){

    }
}
