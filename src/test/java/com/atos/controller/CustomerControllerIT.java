package com.atos.controller;

import com.atos.application.AtosApplication;
import com.atos.helper.StorageHelper;
import com.atos.repository.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AtosApplication.class,CustomerController.class,CustomerRepository.class,StorageHelper.class} , webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerControllerIT {

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    @Test
    public void testGetAllCustomers() throws Exception {
        //Given
        testAddCustomer();

        //When
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/api/customer/all"),
                HttpMethod.GET, entity, String.class);

        //Then
        String expected = "[{id:10001,firstName:Vishal,surName:Arulelango}]";

        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    @Test
    public void testAddCustomer() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String requestJson = "{\n" +
                "  \n" +
                "  \"id\" : 10001,\n" +
                "  \"firstName\" : \"Vishal\",\n" +
                "  \"surName\" : \"Arulelango\"\n" +
                "}";

        HttpEntity<String> entity = new HttpEntity<String>(requestJson, headers);
        restTemplate.put(createURLWithPort("/api/customer/add") , entity);
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

    //Todo - Do the tests for other methods. The idea is the same

}