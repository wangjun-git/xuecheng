package com.xuecheng.consumer_work_quene;

import com.rabbitmq.client.*;
import com.xuecheng.consumer_work_quene.utils.RabbitUtils;

import java.io.IOException;

/**
 * @author wangjun
 * @version 1.0
 * @date 2020/5/18 10:19
 */
public class Consumer2 {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("cmsQueue",false,false,false,null);
        channel.basicQos(1);
        channel.basicConsume("cmsQueue",false,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(new String(body));
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        });
    }
}
