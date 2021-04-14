package org.ecomm.foundation.api;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class LoggerAnnotationPostProcessorTest {

    @TestConfiguration
    static class LoggerAnnotationTestContextConfiguration {

        @Bean
        LoggerAnnotationPostProcessor loggerAnnotationPostProcessor(){
            return new LoggerAnnotationPostProcessor();
        }

        @Bean
        public LoggerTestContainer loggerTestContainer() {
            return new LoggerTestContainer();
        }
    }

    @Autowired
    LoggerTestContainer loggerTestContainer;

    @Test
    public void testLoggerAnnotationInjected() {
        Assertions.assertThat(loggerTestContainer.logger).isNotNull();
    }

    @Test
    public void testLoggerFieldIsOfTypeSlf4j() {
        System.out.printf("logger class = %s\n", loggerTestContainer.logger.getClass());
        Assertions.assertThat(loggerTestContainer.logger.getClass())
          .isAssignableFrom(LoggerFactory.getLogger("Test").getClass());
    }
}
