package org.ecomm.customerservice;

import org.ecomm.foundation.EcommJpaConfiguration;
import org.ecomm.foundation.config.SwaggerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@Import({EcommJpaConfiguration.class, SwaggerConfig.class})
@Configuration
@EnableWebMvc
@EnableJpaRepositories(basePackages = {"org.ecomm.foundation.repo"})
@EntityScan(basePackages = {"org.ecomm.foundation.model"})
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }
}
