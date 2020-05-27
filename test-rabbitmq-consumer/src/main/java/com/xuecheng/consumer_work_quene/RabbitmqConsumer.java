package com.xuecheng.consumer_work_quene;

import com.rabbitmq.client.*;
import com.xuecheng.consumer_work_quene.utils.RabbitUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author wangjun
 * @version 1.0
 * @date 2020/5/17 18:35
 */
public class RabbitmqConsumer {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("cmsQueue",false,false,false,null);
        // 消费队列 1.消息队列的名称,2.确认机制,3.消费的回调函数
        channel.basicConsume("cmsQueue", true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println(new String(body));
            }
        });
//        RabbitUtils.closeConnection(connection,channel);
    }
}
