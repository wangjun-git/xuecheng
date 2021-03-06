package com.xuecheng.consumer_fanout;

import com.rabbitmq.client.*;
import com.xuecheng.consumer_work_quene.utils.RabbitUtils;

import java.io.IOException;

/**
 * @author wangjun
 * @version 1.0
 * @date 2020/5/19 13:56
 */
public class Consumer_F_two {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitUtils.getConnection();
        Channel channel = connection.createChannel();
        // 绑定交换机
        channel.exchangeDeclare("producer_fanout","fanout");
        // 创建临时队列
        String queue = channel.queueDeclare().getQueue();
        // 将队列与交换机绑定
        channel.queueBind(queue,"producer_fanout","");
        // 处理消息
        channel.basicConsume(queue,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(java.lang.String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println(new String(body,"utf-8"));
            }
        });
    }
}
