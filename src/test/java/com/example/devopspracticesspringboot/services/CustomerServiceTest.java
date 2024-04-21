package com.example.devopspracticesspringboot.services;

import com.example.devopspracticesspringboot.models.Customer;
import com.example.devopspracticesspringboot.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CustomerServiceTest {
    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createCustomerTest() {
        Customer newCustomer = new Customer("John", "Doe");
        when(customerRepository.save(newCustomer)).thenReturn(new Customer("John", "Doe"));

        Customer result = customerService.createCustomer(newCustomer);

        assertEquals(newCustomer, result);
        verify(customerRepository, times(1)).save(newCustomer);
    }

    @Test
    public void getCustomerByIdTest() {
        Customer customer = new Customer("John", "Doe");
        when(customerRepository.findById(1L)).thenReturn(customer);

        Customer result = customerService.getCustomerById(1L);

        assertEquals(customer, result);
        verify(customerRepository, times(1)).findById(1L);
    }

    @Test
    public void getCustomersByLastNameTest() {
        Customer customer = new Customer("John", "Doe");
        when(customerRepository.findByLastName("Doe")).thenReturn(List.of(customer));

        var result = customerService.getCustomersByLastName("Doe");

        assertEquals(List.of(customer), result);
        verify(customerRepository, times(1)).findByLastName("Doe");
    }

    @Test
    public void getAllCustomersTest() {
        Customer customer = new Customer("John", "Doe");
        when(customerRepository.findAll()).thenReturn(List.of(customer));

        var result = customerService.getAllCustomers();

        assertEquals(List.of(customer), result);
        verify(customerRepository, times(1)).findAll();
    }
}
