package com.xuecheng.rabbitmq.mq;

import com.rabbitmq.client.Channel;
import com.xuecheng.rabbitmq.config.RabbitmqConfig;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author wangjun
 * @version 1.0
 * @date 2020/5/20 17:54
 */
@Component
public class ConsumerMq {
    @RabbitListener(queues = {RabbitmqConfig.QUEUE_INFROM_CMS})
    public void getMessage(String msg, Message message, Channel channel){
        System.out.println(msg);

    }
}
