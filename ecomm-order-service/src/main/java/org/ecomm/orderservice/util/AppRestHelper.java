package org.ecomm.orderservice.util;

import org.springframework.http.ResponseEntity;
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

    public <T> ResponseEntity<T> postEntityFromService(String url, Class<T> entityClass, Map<String, ?> body) throws Exception {
        RestTemplate template = new RestTemplate();
        return template.postForEntity(url, null, entityClass, body);
    }

}
