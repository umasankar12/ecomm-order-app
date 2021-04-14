package org.ecomm.orderservice;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;

@Configuration
@EnableJms
public class OrderServiceMsgQueueConfig {

    @Bean
    public ActiveMQQueue queue() {
        return new ActiveMQQueue("ecomm.order.queue");
    }

}
