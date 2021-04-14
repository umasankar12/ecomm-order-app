package org.ecomm.orderservice.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.ecomm.foundation.api.AppLogger;
import org.ecomm.foundation.api.AppRequest;
import org.ecomm.foundation.api.AppResponse;
import org.ecomm.foundation.model.*;
import org.ecomm.foundation.repo.OrderRepository;
import org.ecomm.orderservice.dto.CancelOrderPayload;
import org.ecomm.orderservice.facade.OrderFacade;
import org.ecomm.orderservice.facade.OrderValidator;
import org.ecomm.orderservice.util.AppRestHelper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.inject.Inject;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
public class OrderController {

    @Value("${app.customerService.getUser.url}")
    String urlForUser;

    @Value("${app.itemService.getItem.url}")
    String urlForItem;

    @Value("${app.paymentService.getItem.url}")
    String urlForPayment;

    RestTemplate restTemplate = new RestTemplate();

    final JmsTemplate jmsTemplate;

    @AppLogger
    Logger logger;

    OrderRepository orderRepository;
    OrderFacade orderFacade;
    AppRestHelper restHelper;

    @Inject
    public OrderController(OrderRepository orderRepository,
                           OrderFacade orderFacade,
                           AppRestHelper restHelper,
                           JmsTemplate jmsTemplate) {
        this.orderRepository = orderRepository;
        this.orderFacade = orderFacade;
        this.restHelper = restHelper;
        this.jmsTemplate = jmsTemplate;
    }

    /**
     * Provide all orders for a give user.
     * Usually the user id will be extracted from request header using some authentication/login mechanism.
     * For this demo purpose we are skipping the user principal generation process.
     *
     * @return java.util.Collection<Order>
     */
    @GetMapping("/ordersForUser/{userId}")
    public ResponseEntity<List<Order>> getOrdersForUser(@PathVariable("userId") Integer userId) {
        AppRequest<Order> orderRequest = new AppRequest<>(new Order());
        try {
            assert (userId != null);
            logger.info("Going to find orders for {}", userId);
            Customer customer = restTemplate.getForObject(urlForUser + "/" + userId, Customer.class);
            if (customer == null)
                throw new Exception("Invalid Customer Id received " + userId);
            orderRequest.getInput().setCustomer(customer);
            List<Order> orders = orderRepository.findOrdersByCustomer_Id(userId);
            return new ResponseEntity<>(
                    orders,
                    HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(
                    HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/createUserOrder")
    public ResponseEntity<Order> createOrderForUser(@RequestBody Order transOrder) {
        try {
            assert (transOrder.getCustomer().getId() >= 0);
            Integer customerId = transOrder.getInputCustomer().getId();
            logger.info("Going to create new order for {}", customerId);
            Order savedOrder = orderFacade.createOrder(transOrder);

            return new ResponseEntity<>(
                    savedOrder,
                    HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(
                    HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/cancelOrder")
    public ResponseEntity<String> cancelOrder(@RequestBody CancelOrderPayload payload){
        try {
            assert (payload != null);
            logger.info("Going to cancel orders for {}", payload.getOrderId());
            Order order = orderRepository.findById(payload.getOrderId()).get();
            if (order == null)
                throw new Exception("Invalid Customer Id received " + payload.getOrderId());
            order.setStatus("canceled");
            order.setCancelDate(new Timestamp(System.currentTimeMillis()));
            order.setNotes(payload.getNotes());
            logger.info("canceling order for {}", payload.getOrderId());
            orderRepository.save(order);
            logger.info("Refund process started for order {}", payload.getOrderId());
            ResponseEntity<String> responseEntity = new ResponseEntity(String.format("Order canceled for the Order id %s", payload.getOrderId()), HttpStatus.OK);
            return responseEntity;
        } catch (Exception ex) {
            return new ResponseEntity<String>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/queuePosOrder")
    public ResponseEntity<String> addPosOrder(@RequestBody Order transOrder) {
        try {
            //generate request id for the pos order
            String requestId = UUID.randomUUID().toString();
            transOrder.setRequestId(requestId);
            transOrder.setSource("POS");
            transOrder.setSourceId(requestId);
            //publish the order message to kafka
            Map<String, String> inputOrderMessage = new ObjectMapper().convertValue(transOrder, Map.class);
            jmsTemplate.convertAndSend("ecomm.order.queue", inputOrderMessage);
            return new ResponseEntity<>(
                    //AppResponse.successOf(orders, "success", orderRequest),
                    requestId,
                    HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(
                    //AppResponse.failureOf(ex.getMessage(), orderRequest),
                    HttpStatus.BAD_REQUEST);
        }
    }

}
