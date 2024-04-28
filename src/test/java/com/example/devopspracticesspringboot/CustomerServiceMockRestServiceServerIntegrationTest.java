package com.example.devopspracticesspringboot;

import com.example.devopspracticesspringboot.models.Customer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureMockRestServiceServer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@SpringBootTest
@AutoConfigureMockRestServiceServer
@TestPropertySource(locations="classpath:application-test.properties")
@ExtendWith(MockitoExtension.class)
public class CustomerServiceMockRestServiceServerIntegrationTest {
    private RestTemplate restTemplate;
    private String uri;

    private MockRestServiceServer mockServer;

    @Value("classpath:customer.json")
    private Resource resource;

    private Customer customer;

    private String json;

    @BeforeEach
    void setup() throws JsonProcessingException {
        restTemplate = new RestTemplate();
        uri = "http://my-test-uri";

        customer = new Customer("Michael", "Scott");
        ObjectMapper mapper = new ObjectMapper();

        json = mapper.writeValueAsString(customer);
    }

    @Test
    void postRequestTest() {
        mockServer = MockRestServiceServer.bindTo(restTemplate).build();

        mockServer.expect(requestTo(uri + "/customers"))
                .andExpect(method(HttpMethod.POST))
                .andExpect(content().json(json))
                .andRespond(withSuccess());

        ResponseEntity<Customer> response = restTemplate.postForEntity(uri + "/customers", customer, Customer.class);
        mockServer.verify();
        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
    }

    @Test
    void getRequestTest() {
        mockServer = MockRestServiceServer.bindTo(restTemplate).build();

        mockServer.expect(requestTo(uri + "/customers?lastName=Scott"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(resource, MediaType.APPLICATION_JSON));

        Customer response = restTemplate.getForObject(uri + "/customers?lastName=Scott", Customer.class);
        mockServer.verify();
        assertEquals(customer, response);
    }
}
