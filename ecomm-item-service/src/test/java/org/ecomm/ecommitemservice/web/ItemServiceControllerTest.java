package org.ecomm.ecommitemservice.web;

import org.ecomm.ecommitemservice.service.ItemService;
import org.ecomm.foundation.api.LoggerAnnotationPostProcessor;
import org.ecomm.foundation.model.Item;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ItemServiceControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Mock
    private ItemService service;

    @Autowired
    WebApplicationContext wac;

    @MockBean
    public LoggerAnnotationPostProcessor loggerAnnotationPostProcessor;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");

    @Test
    void getItem() throws Exception {

        Item item = new Item();
        java.util.Date date = sdf.parse("2021-04-12");
        item.setCode("1212").setId(1).setName("Sugar").setDescription("1212")
                .setAvailQty(BigInteger.TEN).setUnit("14")
                .setUnitPrice(BigInteger.valueOf(4)).setTags("1234564")
        .setStartDate(new Date(date.getTime()))
        .setEndDate(new Date(date.getTime()));
        Mockito.when(service.findItemByItemCode(any(String.class)))
                .thenReturn(item);


        ResponseEntity<Item> response = restTemplate.getForEntity(
                new URL("http://localhost:10002/item-service/item/1212").toString(), Item.class);
        assertNotNull(response.getBody());
    }

    @Test
    void addItem() throws ParseException, URISyntaxException {
        Item item = new Item();
        java.util.Date date = sdf.parse("2021-04-12");
        item.setCode("1212").setName("Sugar").setDescription("1212")
                .setAvailQty(BigInteger.TEN).setUnit("14")
                .setUnitPrice(BigInteger.valueOf(4)).setTags("1234564")
                .setStartDate(new Date(date.getTime()))
                .setEndDate(new Date(date.getTime()));
        Mockito.when(service.findItemByItemCode(any(String.class)))
                .thenReturn(item);

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Item> request = new HttpEntity<>(item, headers);

        ResponseEntity<Item> itemResponseEntity = restTemplate.postForEntity(new URI("http://localhost:10002/item-service/addItem").toString(),
                request,Item.class);

        assertNotNull(itemResponseEntity.getBody());
    }

    @Test
    void findAllItemsById() throws ParseException, MalformedURLException {
        Item item = new Item();
        java.util.Date date = sdf.parse("2021-04-12");
        item.setCode("1212").setId(1).setName("Sugar").setDescription("1212")
                .setAvailQty(BigInteger.TEN).setUnit("14")
                .setUnitPrice(BigInteger.valueOf(4)).setTags("1234564")
                .setStartDate(new Date(date.getTime()))
                .setEndDate(new Date(date.getTime()));
        Mockito.when(service.findItemByItemCode(any(String.class)))
                .thenReturn(item);


        HttpHeaders headers = new HttpHeaders();

        HttpEntity<List<Integer>> request = new HttpEntity<>(Arrays.asList(1,2), headers);
    }

}