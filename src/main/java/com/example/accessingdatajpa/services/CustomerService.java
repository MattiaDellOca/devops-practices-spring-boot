package com.example.accessingdatajpa.services;

import com.example.accessingdatajpa.models.Customer;
import com.example.accessingdatajpa.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository repository;

    @Transactional(isolation= Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public Customer createCustomer(Customer customer) {
        return repository.save(new Customer(customer.getFirstName(), customer.getLastName()));
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, readOnly = true)
    public Customer getCustomerById(long id) {
        return repository.findById(id);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, readOnly = true)
    public List<Customer> getCustomersByLastName(String lastName) {
        return repository.findByLastName(lastName);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, readOnly = true)
    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        repository.findAll().forEach(customers::add);
        return customers;
    }
}
