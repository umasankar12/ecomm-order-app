package org.ecomm.orderservice.web;

import org.ecomm.foundation.api.AppLogger;
import org.ecomm.foundation.api.AppRequest;
import org.ecomm.foundation.api.AppResponse;
import org.ecomm.foundation.model.Customer;
import org.ecomm.foundation.model.Order;
import org.ecomm.foundation.repo.OrderRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.inject.Inject;
import java.util.List;

@RestController
public class OrderController {

    @Value("${app.customerService.getUser.url}")
    String urlForUser;

    RestTemplate restTemplate = new RestTemplate();

    @AppLogger
    Logger logger;

    OrderRepository orderRepository;

    @Inject
    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    /**
     * Provide all orders for a give user.
     * Usually the user id will be extracted from request header using some authentication/login mechanism.
     * For this demo purpose we are skipping the user principal generation process.
     *
     * @return java.util.Collection<Order>
     */
    @GetMapping("/ordersForUser/{userId}")
    public AppResponse<Order> getOrdersForUser(@PathVariable("userId") Integer userId) {
        AppRequest<Order> orderRequest = new AppRequest<>(new Order());
        try {
            assert (userId != null);
            logger.info("Going to find orders for {}", userId);
            Customer customer = restTemplate.getForObject(urlForUser + "/" + userId, Customer.class);
            if (customer == null)
                throw new Exception("Invalid Customer Id received " + userId);
            orderRequest.getInput().setCustomer(customer);
            List<Order> orders = orderRepository.findOrdersByCustomer_Id(userId);
            return AppResponse.successOf(orders, "success", orderRequest);
        } catch (Exception ex) {
            return AppResponse.failureOf(ex.getMessage(), orderRequest);
        }
    }


}
