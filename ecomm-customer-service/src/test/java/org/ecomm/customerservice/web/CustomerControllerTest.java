package org.ecomm.customerservice.web;

import org.ecomm.customerservice.service.AddressService;
import org.ecomm.customerservice.service.CustomerService;
import org.ecomm.foundation.api.LoggerAnnotationPostProcessor;
import org.ecomm.foundation.model.Customer;
import org.ecomm.foundation.model.CustomerPayment;
import org.ecomm.foundation.model.Item;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CustomerControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Mock
    private CustomerService customerService;

    @Mock
    private AddressService addressService;


    @Autowired
    WebApplicationContext wac;

    @MockBean
    public LoggerAnnotationPostProcessor loggerAnnotationPostProcessor;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    void saveCustomer() {
    }

    @Test
    void getCustomers() throws MalformedURLException {
        ResponseEntity<List> response = restTemplate.getForEntity(
                new URL("http://localhost:10003/customer-service/getCustomers").toString(), List.class);
        assertNotNull(response.getBody());
    }

    @Test
    void getCustomerById() throws MalformedURLException {
        ResponseEntity<Customer> response = restTemplate.getForEntity(
                new URL("http://localhost:10003/customer-service/get/2").toString(), Customer.class);
        assertNotNull(response.getBody());
    }

    @Test
    void getAllAddress() throws MalformedURLException {
        ResponseEntity<List> response = restTemplate.getForEntity(
                new URL("http://localhost:10003/customer-service/getAllAddress").toString(), List.class);
        assertNotNull(response.getBody());
    }
}