package com.xuecheng.producer_work_quene;

import com.rabbitmq.client.*;
import com.xuecheng.producer_work_quene.utils.RabbitUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author wangjun
 * @version 1.0
 * @date 2020/5/17 9:33
 */
public class RabbitmqProducer {


    public static void main(String[] args) throws IOException, TimeoutException {
        //创建工厂连接对象
   /*     ConnectionFactory connectionFactory = new ConnectionFactory();
        // 连接到mq的主机ip
        connectionFactory.setHost("localhost");
        // 连接到mq主机端口号
        connectionFactory.setPort(5672);
        //设置连接到那个虚拟机(虚拟机名称)
        connectionFactory.setVirtualHost("/cms");
        // 用户名,密码
        connectionFactory.setUsername("wangjun");
        connectionFactory.setPassword("wj123456");
        // 创建连接
        Connection connection = connectionFactory.newConnection();*/
        Connection connection = RabbitUtils.getConnection();
        // 创建通道对象
        Channel channel = connection.createChannel();
        // 通道绑定对应的消息队列
        // 1.队列名称(不存在自动创建),2.是否持久化,3.是否独占队列,4.是否消费完成自动删除队列,5.额外附加参数
        channel.queueDeclare("cmsQueue",false,false,false,null);
        //发布消息
        //1.交换机名称,2.队列名称,3.传递消息额外设置,4.消息内容
        channel.basicPublish("","cmsQueue",null,"hello,rabbitmq".getBytes());
        //关闭
        RabbitUtils.closeConnection(connection,channel);
    }
}
