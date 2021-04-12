package org.ecomm.paymentservice;

import com.zaxxer.hikari.HikariDataSource;
import org.ecomm.foundation.EcommJpaConfiguration;
import org.ecomm.foundation.api.LoggerAnnotationPostProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.cdi.Eager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.TransactionManager;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.inject.Singleton;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

//@Configuration
public class EcommPaymentServiceConfig {

//    @Value("${app.jpa.persistenceXml}")
//    private String persistenceXml;
//
//    @Bean
//    public String persistenceXmlLocation() {
//        return persistenceXml;
//    }
//
//    @Bean
//    @Singleton
//    public LoggerAnnotationPostProcessor loggerAnnotationPostProcessor() {
//        return new LoggerAnnotationPostProcessor();
//    }
//
//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//        var emf = new LocalContainerEntityManagerFactoryBean();
//        emf.setPersistenceXmlLocation(persistenceXmlLocation());
//        emf.setPersistenceUnitName("payment-service-pu");
//        //emf.setDataSource(dataSource());
//        return emf;
//    }
//
////    @Bean
////    public DataSource dataSource() {
////        return DataSourceBuilder.create().build();
////    }
//
//    @Bean
//    public TransactionManager transactionManager(EntityManagerFactory emf) {
//        return new JpaTransactionManager(emf);
//    }


}
