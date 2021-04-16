package org.ecomm.orderservice.web;

import org.ecomm.foundation.api.AppLogger;
import org.slf4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Named
public class AuthenticationInterceptor implements HandlerInterceptor {

    @AppLogger
    Logger logger;

    @Override
    public boolean preHandle(
      HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //this is where the URL should be checked for auth mechanism
        String authKey = request.getHeader("customer-auth-token");
        logger.info("authentication token found: {}", authKey);
        return true;
    }

    @Override
    public void postHandle(
      HttpServletRequest request,
      HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle call");
    }

    @Override
    public void afterCompletion(
      HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion call");
    }
}
