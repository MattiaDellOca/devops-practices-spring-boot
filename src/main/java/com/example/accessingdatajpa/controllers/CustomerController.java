package com.example.accessingdatajpa.controllers;

import com.example.accessingdatajpa.models.Customer;
import com.example.accessingdatajpa.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
public class CustomerController {
    @Autowired()
    private CustomerService service;

    @GetMapping(value = "/customers")
    public ResponseEntity<List<Customer>> getCustomers(@RequestParam(value = "lastName", required = false) String lastName) {
        try {
            List<Customer> customers;
            if(lastName == null || lastName.isEmpty()) {
                customers = service.getAllCustomers();
            } else {
                customers = service.getCustomersByLastName(lastName);
            }
            if(customers.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            return new ResponseEntity<>(customers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/customers")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        try {
            Customer newCustomer = service.createCustomer(customer);
            return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
