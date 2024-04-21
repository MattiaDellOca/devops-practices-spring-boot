package com.example.devopspracticesspringboot.controllers;

import com.example.devopspracticesspringboot.models.Customer;
import com.example.devopspracticesspringboot.services.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CustomerControllerTest {
    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    private final List<Customer> customers = Arrays.asList(
            new Customer("John", "Doe"),
            new Customer("Jane", "Doe"),
            new Customer("Anne", "Smith")
    );

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAllCustomersTest() {
        when(customerService.getAllCustomers()).thenReturn(customers);

        ResponseEntity<List<Customer>> result = customerController.getCustomers(null);

        assertEquals(customers, result.getBody());
        assertEquals(HttpStatus.OK, result.getStatusCode());
        verify(customerService, times(1)).getAllCustomers();
        verify(customerService, never()).getCustomersByLastName(anyString());
    }

    @Test
    public void getCustomersByLastNameTest() {
        String lastName = "Doe";
        List<Customer> filteredCustomers = customers.stream()
                .filter(c -> c.getLastName().equals(lastName))
                .toList();
        when(customerService.getCustomersByLastName(lastName)).thenReturn(filteredCustomers);

        ResponseEntity<List<Customer>> result = customerController.getCustomers(lastName);

        assertEquals(filteredCustomers, result.getBody());
        assertEquals(HttpStatus.OK, result.getStatusCode());
        verify(customerService, never()).getAllCustomers();
        verify(customerService, times(1)).getCustomersByLastName(lastName);
    }

    @Test
    public void getCustomersEmptyLastNameTest() {
        when(customerService.getAllCustomers()).thenReturn(customers);

        ResponseEntity<List<Customer>> result = customerController.getCustomers("");

        assertEquals(customers, result.getBody());
        assertEquals(HttpStatus.OK, result.getStatusCode());
        verify(customerService, times(1)).getAllCustomers();
        verify(customerService, never()).getCustomersByLastName(anyString());
    }

    @Test
    public void getCustomersEmptyResultTest() {
        when(customerService.getAllCustomers()).thenReturn(List.of());

        ResponseEntity<List<Customer>> result = customerController.getCustomers(null);

        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
        verify(customerService, times(1)).getAllCustomers();
        verify(customerService, never()).getCustomersByLastName(anyString());
    }

    @Test
    public void getCustomersErrorTest() {
        when(customerService.getAllCustomers()).thenThrow(new RuntimeException());

        ResponseEntity<List<Customer>> result = customerController.getCustomers(null);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
        verify(customerService, times(1)).getAllCustomers();
        verify(customerService, never()).getCustomersByLastName(anyString());
    }

    @Test
    public void createCustomerTest() {
        Customer newCustomer = new Customer("John", "Doe");
        when(customerService.createCustomer(newCustomer)).thenReturn(newCustomer);

        ResponseEntity<Customer> result = customerController.createCustomer(newCustomer);

        assertEquals(newCustomer, result.getBody());
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        verify(customerService, times(1)).createCustomer(newCustomer);
    }

    @Test
    public void createCustomerErrorTest() {
        Customer newCustomer = new Customer("John", "Doe");
        when(customerService.createCustomer(newCustomer)).thenThrow(new RuntimeException());

        ResponseEntity<Customer> result = customerController.createCustomer(newCustomer);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
        verify(customerService, times(1)).createCustomer(newCustomer);
    }
}
