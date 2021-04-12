package org.ecomm.foundation;

import org.ecomm.foundation.api.LoggerAnnotationPostProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.TransactionManager;

import javax.inject.Singleton;
import javax.persistence.EntityManagerFactory;

@Configuration
public class EcommJpaConfiguration {

    @Value("${app.jpa.persistenceXml}")
    private String persistenceXml;

    @Bean
    public String persistenceXmlLocation() {
        return persistenceXml;
    }

    @Bean
    @Singleton
    public LoggerAnnotationPostProcessor loggerAnnotationPostProcessor() {
        return new LoggerAnnotationPostProcessor();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        var emf = new LocalContainerEntityManagerFactoryBean();
        emf.setPersistenceXmlLocation(persistenceXmlLocation());
        emf.setPersistenceUnitName("payment-service-pu");
        //emf.setDataSource(dataSource());
        return emf;
    }

//    @Bean
//    public DataSource dataSource() {
//        return DataSourceBuilder.create().build();
//    }

    @Bean
    public TransactionManager transactionManager(EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }

}
