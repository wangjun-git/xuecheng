package com.xuecheng.manage_cms_client.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wangjun
 * @version 1.0
 * @date 2020/5/21 13:46
 */
@Configuration
public class CmsClientConfig {
    public static String EX_ROUTING_CMS_POSTPAGE ="ex_routing_cms_postpage";
    // 队列名称
    @Value("${xuecheng.mq.queue}")
    private String qName;
    // routkey名称
    @Value("${xuecheng.mq.routingKey}")
    private String routKey;
    @Bean("queue")
    public Queue getQueue(){
        return new Queue(qName);
    }
    @Bean("exchange")
    public Exchange getExchange(){
        Exchange exchange = ExchangeBuilder.topicExchange(EX_ROUTING_CMS_POSTPAGE).durable(false).build();
        return exchange;
    }
    // 将交换机和队列绑定
    @Bean
    public Binding exchangeBindQueue(@Qualifier("queue")Queue queue,
                                      @Qualifier("exchange") Exchange exchange){
        Binding bind = BindingBuilder.bind(queue).to(exchange).with(routKey).noargs();
        return bind;
    }
}
