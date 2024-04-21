package com.example.devopspracticesspringboot.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CustomerTest {
    private Customer customer;

    @BeforeEach
    public void setUp() {
        customer = new Customer("John", "Doe");
    }

    @Test
    public void constructTest() {
        Assertions.assertEquals("John", customer.getFirstName());
        Assertions.assertEquals("Doe", customer.getLastName());
    }

    @Test
    public void gettersAndSetterTest() {
        customer.setFirstName("Micael");
        customer.setLastName("Jackson");
        Assertions.assertNull(customer.getId());
        Assertions.assertEquals("Micael", customer.getFirstName());
        Assertions.assertEquals("Jackson", customer.getLastName());
    }

    @Test
    public void toStringTest() {
        Assertions.assertEquals("Customer[id=null, firstName='John', lastName='Doe']", customer.toString());
    }

    @Test
    public void equalsTest() {
        Assertions.assertEquals(customer, new Customer("John", "Doe"));
        Assertions.assertNotEquals(customer, new Customer("Michael", "Scott"));
        Assertions.assertNotEquals(customer, new Customer("Anne", "Doe"));
        Assertions.assertNotEquals(customer, new Customer("John", "Scott"));
        Assertions.assertEquals(customer, customer);
        Assertions.assertNotEquals(customer, null);
        Assertions.assertNotEquals(customer, new Object());
    }

    @Test
    public void hashCodeTest() {
        Assertions.assertEquals(customer.hashCode(), new Customer("John", "Doe").hashCode());
        Assertions.assertNotEquals(customer.hashCode(), new Customer("Michael", "Scott").hashCode());
        Assertions.assertNotEquals(customer.hashCode(), new Customer("Anne", "Doe").hashCode());
        Assertions.assertNotEquals(customer.hashCode(), new Customer("John", "Scott").hashCode());
    }
}
