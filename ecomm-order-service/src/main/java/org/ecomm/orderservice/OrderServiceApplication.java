package org.ecomm.orderservice;

import org.ecomm.foundation.EcommJpaConfiguration;
import org.ecomm.foundation.config.SwaggerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
@EnableJpaRepositories(basePackages = {"org.ecomm.foundation.repo"})
@EntityScan(basePackages = {"org.ecomm.foundation.model"})
@Import({EcommJpaConfiguration.class, SwaggerConfig.class})
public class OrderServiceApplication {

    public static void main(String[] args) {
        System.out.println("Welcome to customer service");
        SpringApplication.run(OrderServiceApplication.class);
    }
}
