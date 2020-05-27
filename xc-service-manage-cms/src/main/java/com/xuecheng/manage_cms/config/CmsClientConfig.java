package com.xuecheng.manage_cms.config;

import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
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
    @Bean
    public Exchange getExchange(){
        Exchange exchange = ExchangeBuilder.topicExchange(EX_ROUTING_CMS_POSTPAGE).durable(true).build();
        return exchange;
    }

}
