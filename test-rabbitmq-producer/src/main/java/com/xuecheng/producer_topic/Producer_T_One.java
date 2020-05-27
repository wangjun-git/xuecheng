package com.xuecheng.producer_topic;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.xuecheng.producer_work_quene.utils.RabbitUtils;

import java.io.IOException;

/**
 * @author wangjun
 * @version 1.0
 * @date 2020/5/20 10:58
 */
public class Producer_T_One {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitUtils.getConnection();
        Channel channel = connection.createChannel();
        // 定义交换器
        channel.exchangeDeclare("producer_topic", BuiltinExchangeType.TOPIC);
        String routKey = "cms.message";
        channel.basicPublish("producer_topic",routKey,null,
                ("111").getBytes());
        channel.basicPublish("producer_topic",routKey,null,
                ("222").getBytes());
        channel.basicPublish("producer_topic",routKey,null,
                ("333").getBytes());
        RabbitUtils.closeConnection(connection,channel);
    }
}
