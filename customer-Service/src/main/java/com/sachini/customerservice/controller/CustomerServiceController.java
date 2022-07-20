package com.sachini.customerservice.controller;

import com.sachini.customerservice.service.CustomerServiceImplementation;
import com.sachini.model.customer.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/services/customer")
public class CustomerServiceController {

    CustomerServiceImplementation customerServiceImplementation;

    @Autowired
    public CustomerServiceController(CustomerServiceImplementation customerServiceImplementation) {
        this.customerServiceImplementation = customerServiceImplementation;
    }

    @PostMapping
    public Customer save(@RequestBody Customer customer){
        return customerServiceImplementation.save(customer);
    }

    @GetMapping(value = "/{id}")
    public Customer getCustomer(@PathVariable int id){
        System.out.println("recuest came");
        return customerServiceImplementation.findById(id);
    }

    @GetMapping
    public List<Customer> getAllCustomers(){
        return customerServiceImplementation.findAll();
    }
}
