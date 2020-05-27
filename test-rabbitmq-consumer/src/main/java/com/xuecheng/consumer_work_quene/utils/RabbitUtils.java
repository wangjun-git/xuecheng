package com.xuecheng.consumer_work_quene.utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author wangjun
 * @version 1.0
 * @date 2020/5/18 9:55
 */
public class RabbitUtils {
        private static ConnectionFactory connectionFactory;
    static {
        connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("wangjun");
        connectionFactory.setPassword("wj123456");
        connectionFactory.setVirtualHost("/cms");
    }
    public static Connection getConnection(){
        try {
            return connectionFactory.newConnection();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public static void closeConnection(Connection connection, Channel channel){
        if(channel != null){
            try {
                channel.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        if(connection != null){
            try {
                connection.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        }

    }
}
