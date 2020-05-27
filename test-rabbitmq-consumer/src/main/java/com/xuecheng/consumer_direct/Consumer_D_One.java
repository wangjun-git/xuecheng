package com.xuecheng.consumer_direct;

import com.rabbitmq.client.*;
import com.xuecheng.consumer_work_quene.utils.RabbitUtils;

import java.io.IOException;

/**
 * @author wangjun
 * @version 1.0
 * @date 2020/5/19 15:04
 */
public class Consumer_D_One {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitUtils.getConnection();
        Channel channel = connection.createChannel();
        // 绑定交换机
        channel.exchangeDeclare("producer_direct","direct");
        //创建临时列队
        String queue = channel.queueDeclare().getQueue();
        //列队绑定交换机
        channel.queueBind(queue,"producer_direct","cuowu");
        channel.queueBind(queue,"producer_direct","zhengque");
        //处理消息
        channel.basicConsume(queue,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println(new String(body,"utf-8"));
            }
        });
    }
}
