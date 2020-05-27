package com.xuecheng.rabbitmq.config;

import com.rabbitmq.client.AMQP;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wangjun
 * @version 1.0
 * @date 2020/5/20 15:33
 */
@Configuration
public class RabbitmqConfig {
    public static final String QUEUE_INFROM_EMAIL = "queue_infrom_email";
    public static final String QUEUE_INFROM_CMS = "queue_infrom_cms";
    public static final String EXCHANGE_TOPICS_INFROM = "exchange_topics_infrom";

    // 声明cms队列
    @Bean(QUEUE_INFROM_CMS)
    public Queue getQueue1(){
        Queue queue = new Queue(QUEUE_INFROM_CMS);
        return queue;
    }
    // 声明email队列
    @Bean(QUEUE_INFROM_EMAIL)
    public Queue getQueue2(){
        Queue queue = new Queue(QUEUE_INFROM_EMAIL);
        return queue;
    }
    //声明交换机
    @Bean(EXCHANGE_TOPICS_INFROM)
    public Exchange getExchang(){
        Exchange exchang = ExchangeBuilder.topicExchange(EXCHANGE_TOPICS_INFROM).durable(true).build();
        return exchang;
    }
    // 绑定交换机和列队cms
    @Bean
    public Binding bindCms(@Qualifier(QUEUE_INFROM_CMS) Queue queue,
                            @Qualifier(EXCHANGE_TOPICS_INFROM) Exchange exchange){
        Binding bind = BindingBuilder.bind(queue).to(exchange).with("#.cms.#").noargs();
        return bind;
    }
    // 绑定交换机和列队email
    public Binding bindEmial(@Qualifier(QUEUE_INFROM_EMAIL)Queue queue,
                              @Qualifier(EXCHANGE_TOPICS_INFROM)Exchange exchange){
        Binding bind = BindingBuilder.bind(queue).to(exchange).with("#.email.#").noargs();
        return bind;
    }

}
