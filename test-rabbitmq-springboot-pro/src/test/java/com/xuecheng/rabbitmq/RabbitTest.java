package com.xuecheng.rabbitmq;

import com.xuecheng.rabbitmq.config.RabbitmqConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author wangjun
 * @version 1.0
 * @date 2020/5/20 16:44
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RabbitTest {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Test
    public void test1(){
        rabbitTemplate.convertAndSend
                (RabbitmqConfig.EXCHANGE_TOPICS_INFROM,"cms.email","hello1");

    }

}
