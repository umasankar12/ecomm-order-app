package org.ecomm.posclient;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import javax.inject.Named;
import javax.inject.Singleton;

@Named
@Singleton
public class EcommPosHttpClient {

    public EcommPosHttpClient() {
    }

    public <T> ResponseEntity<T> doPost(String url, Class<T> entityClass, Object body) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String requestBody = new ObjectMapper().writeValueAsString(body);
        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
        RestTemplate template = new RestTemplate();
        return template.postForEntity(url, request, entityClass);
    }

}
