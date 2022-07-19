package com.sachini.musicc.controller;

import com.sachini.musicc.model.Customer;
import com.sachini.musicc.service.CustomerServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerServiceImpl customerServiceimpl;

    public CustomerController(CustomerServiceImpl customerService) {
        this.customerServiceimpl = customerService;
    }

    @GetMapping("/save")
    public ResponseEntity<Customer> fetchCustomer(@RequestParam int id){
        Customer customer = customerServiceimpl.fetchCustomer(id);
        if(customer == null){
            return ResponseEntity.notFound().build();
        }else {
            return ResponseEntity.ok().body(customer);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer){
        Customer customer1 = customerServiceimpl.addCustomer(customer);
        return new ResponseEntity<>(customer1, HttpStatus.CREATED);
    }
}
