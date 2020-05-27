package com.xuecheng.producer_direct;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.xuecheng.producer_work_quene.utils.RabbitUtils;

import java.io.IOException;

/**
 * @author wangjun
 * @version 1.0
 * @date 2020/5/19 14:54
 */
public class Producer_D {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitUtils.getConnection();
        Channel channel = connection.createChannel();
        // 定义rout
        String key = "zhengque";
        // 声明交换机
        channel.exchangeDeclare("producer_direct","direct");
        // 发送消息
        channel.basicPublish("producer_direct",key,null,("指定的"+key+"进行发送").getBytes());
        RabbitUtils.closeConnection(connection,channel);

    }
}
