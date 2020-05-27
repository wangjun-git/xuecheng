package com.xuecheng.consumer_topic;

import com.rabbitmq.client.*;
import com.xuecheng.consumer_work_quene.utils.RabbitUtils;

import java.io.IOException;

/**
 * @author wangjun
 * @version 1.0
 * @date 2020/5/20 11:14
 */
public class Consumer_T_Three {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitUtils.getConnection();
        Channel channel = connection.createChannel();
        // 绑定交换机
        channel.exchangeDeclare("producer_topic", BuiltinExchangeType.TOPIC);
        //创建临时队列
        String queue = channel.queueDeclare("three",false,false,false,null).getQueue();
        //队列绑定交换机
        channel.queueBind(queue,"producer_topic","#.cms.#.message.#");
        // 处理消息
        channel.basicConsume(queue,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println(new String(body));
            }
        });
    }

}
