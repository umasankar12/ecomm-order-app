package org.ecomm.foundation.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class LoggerAnnotationPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        var fields = bean.getClass().getDeclaredFields();
        for (var field : fields) {
            if (field.isAnnotationPresent(AppLogger.class) && field.getType().isAssignableFrom(Logger.class)) {
                System.out.printf("Logger annotation based field found in bean: %s of type %s \n",
                  beanName, bean.getClass());

                try {
                    field.setAccessible(true);
                    String loggerName = field.getAnnotation(AppLogger.class).value();
                    if (loggerName.isEmpty())
                        loggerName = bean.getClass().getName();
                    System.out.printf("Logger name = %s\n", loggerName);
                    Object logger = field.get(bean);
                    if (logger == null) {
                        logger = LoggerFactory.getLogger(loggerName);
                        field.set(bean, logger);
                    }


                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

            }
        }
        return bean;
    }
}
