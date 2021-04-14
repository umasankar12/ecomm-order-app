package org.ecomm.orderservice.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.inject.Named;
import javax.inject.Singleton;
import java.util.Map;

@Named
@Singleton
public class AppRestHelper {

    public AppRestHelper() {
    }

    public <T> T getEntityFromService(String url, Class<T> entityClass) throws Exception {
        RestTemplate template = new RestTemplate();
        return template.getForObject(url, entityClass);
    }

    public <T> ResponseEntity<T> postEntityFromService(String url, Class<T> entityClass, Object body) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String requestBody = new ObjectMapper().writeValueAsString(body);
        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
        RestTemplate template = new RestTemplate();
        return template.postForEntity(url, request, entityClass);
    }

}
