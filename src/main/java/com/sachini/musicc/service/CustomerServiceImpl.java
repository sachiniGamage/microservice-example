package com.sachini.musicc.service;

import com.sachini.musicc.model.Customer;
import com.sachini.musicc.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl {

    private final CustomerRepository customerRopository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRopository) {
        this.customerRopository = customerRopository;
    }

    //get all customers
    public List<Customer> getCustomers(){
        return customerRopository.findAll();
    }

    //add new customer
    public Customer addCustomer(Customer customer){
        return customerRopository.save(customer);
    }

    //get customer by providing id
    public Customer fetchCustomer(int id){
        Optional<Customer> cust = customerRopository.findById(id);
        if(cust.isPresent()){
            return cust.get();
        }else{
            return null;
        }
    }
}
