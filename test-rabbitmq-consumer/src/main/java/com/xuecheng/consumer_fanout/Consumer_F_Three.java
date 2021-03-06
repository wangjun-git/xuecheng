package com.xuecheng.consumer_fanout;

import com.rabbitmq.client.*;
import com.xuecheng.consumer_work_quene.utils.RabbitUtils;

import java.io.IOException;

/**
 * @author wangjun
 * @version 1.0
 * @date 2020/5/19 14:09
 */
public class Consumer_F_Three {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitUtils.getConnection();
        Channel channel = connection.createChannel();
        // 绑定交换机
        channel.exchangeDeclare("producer_fanout","fanout");
       // 创建临时列队
        String queue = channel.queueDeclare().getQueue();
        // 将列队与交换机绑定
        channel.queueBind(queue,"producer_fanout","");
        // 处理消息
        channel.basicConsume(queue,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println(new String(body,"utf-8"));
            }
        });
    }
}
