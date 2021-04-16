package org.ecomm.orderservice;

import org.ecomm.orderservice.web.AuthenticationInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class OrderServiceInterceptorConfig implements WebMvcConfigurer {

    @Inject
    AuthenticationInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        WebMvcConfigurer.super.addInterceptors(registry);
        registry.addInterceptor(authInterceptor)
        .addPathPatterns("/**")
        .excludePathPatterns("/unsecured/**");
    }
}
