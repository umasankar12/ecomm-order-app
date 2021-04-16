package org.ecomm.ecommitemservice;

import org.ecomm.foundation.api.LoggerAnnotationPostProcessor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.inject.Singleton;

@TestConfiguration()
@EnableAutoConfiguration
@EnableJpaRepositories
public class JpaTestConfiguration {

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        var emf = new LocalContainerEntityManagerFactoryBean();
        emf.setPersistenceUnitName("payment-service-pu");
        //emf.setDataSource(dataSource());
        return emf;
    }

}
