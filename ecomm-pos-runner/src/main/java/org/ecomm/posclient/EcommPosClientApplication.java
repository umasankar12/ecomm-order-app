package org.ecomm.posclient;

import org.ecomm.foundation.EcommJpaConfiguration;
import org.ecomm.foundation.api.AppLogger;
import org.ecomm.foundation.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;

import javax.inject.Inject;
import java.nio.file.Files;
import java.util.List;

@SpringBootApplication
@Import(EcommJpaConfiguration.class)
public class EcommPosClientApplication implements CommandLineRunner {

    static class OrderNotFoundException extends Exception {
        public OrderNotFoundException() {
            super("Error reading file. No Orders found in file");
        }
    }

    @AppLogger
    Logger logger = LoggerFactory.getLogger(EcommPosClientApplication.class);

    @Inject
    BulkOrderReader orderReader;

    @Inject
    EcommPosHttpClient httpClient;

    @Value("${app.customerService.queueOrder.url}")
    String posOrderPostUrl;

    final String orderPath = "classpath:pos_bulk_order.json";

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(EcommPosClientApplication.class);
        application.setWebApplicationType(WebApplicationType.NONE);
        application.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("Ecom POS Service runner started");
        String path = ResourceUtils.getFile(orderPath).getPath();
        orderReader.readOrders(path)
                .orElseThrow(OrderNotFoundException::new)
                .forEach(this::createOrder);

        logger.info("Order processing completed");
    }

    private void createOrder(Order order){
        try {
            logger.info("going to post: {}", order);
            ResponseEntity<String> response = httpClient.doPost(posOrderPostUrl, String.class, order);
            if (response.getStatusCode() == HttpStatus.OK) {
                String confirmationId = response.getBody();
                logger.info("confirmation id = {}", confirmationId);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info("order processing failed");
        }
    }
}
