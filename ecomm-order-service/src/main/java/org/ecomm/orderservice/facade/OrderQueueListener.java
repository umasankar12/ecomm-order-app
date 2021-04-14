package org.ecomm.orderservice.facade;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.ecomm.foundation.api.AppLogger;
import org.ecomm.foundation.model.Order;
import org.slf4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationContextInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.web.context.ContextLoaderListener;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;
import javax.inject.Singleton;
import javax.servlet.ServletContextEvent;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Named
@Singleton
public class OrderQueueListener { // implements ApplicationListener<ContextRefreshedEvent> {

    @Value("${app.order.queue.name}")
    String _orderQueueName;

    @AppLogger
    Logger logger;

    @Inject
    Provider<OrderFacade> orderFacade;

    public void onApplicationEvent(ContextRefreshedEvent event) {
        logger.info("Context Initialized: OrderQueueListener");

    }


    @JmsListener(destination = "ecomm.order.queue")
    public void onMessagge(Map<String, String> message) {
        System.out.println("message received as : " + message);
        for (var item: message.entrySet()) {
            System.out.printf("%s = %s \n", item.getKey(), item.getValue());
        }
//        try {
//            //Map<String, String> map = new ObjectMapper().readValue(message.toString(), Map.class);
//
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }

        //Now we have the message body
        Order inputOrder = new ObjectMapper().convertValue(message, Order.class);
        try {
            Order savedOrder = orderFacade.get().createOrder(inputOrder);
            logger.info("order created with order id: {}", savedOrder.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
