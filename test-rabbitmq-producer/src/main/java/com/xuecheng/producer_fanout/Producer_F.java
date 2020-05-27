package com.xuecheng.producer_fanout;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.xuecheng.producer_work_quene.utils.RabbitUtils;

import java.io.IOException;

/**
 * @author wangjun
 * @version 1.0
 * @date 2020/5/19 13:33
 */
public class Producer_F {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitUtils.getConnection();
        Channel channel = connection.createChannel();
        // 声明交换机
        channel.exchangeDeclare("producer_fanout","fanout");
        // 发送消息
        channel.basicPublish("producer_fanout","",null,
                "rabbitmq广播模式".getBytes());
        RabbitUtils.closeConnection(connection,channel);
    }

}
