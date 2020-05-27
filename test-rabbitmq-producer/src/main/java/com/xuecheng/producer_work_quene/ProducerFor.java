package com.xuecheng.producer_work_quene;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.xuecheng.producer_work_quene.utils.RabbitUtils;

import java.io.IOException;

/**
 * @author wangjun
 * @version 1.0
 * @date 2020/5/18 10:30
 */
public class ProducerFor {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("cmsQueue",false,false,false,null);
        for (int i = 0; i <20 ; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            channel.basicPublish("","cmsQueue",true,null,("hello"+i).getBytes());
        }
        RabbitUtils.closeConnection(connection,channel);
    }
}
