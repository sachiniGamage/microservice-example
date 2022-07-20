package com.sachini.customerservice.service;

import com.sachini.customerservice.repository.CustomerRepository;
import com.sachini.model.customer.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImplementation {
    CustomerRepository customerRepositoryl;

    @Autowired
    public CustomerServiceImplementation(CustomerRepository customerRepositoryl) {
        this.customerRepositoryl = customerRepositoryl;
    }

//    @Override
    public Customer save(Customer customer){
        return customerRepositoryl.save(customer);
    }
//    @Override
    public Customer findById(int id){
        Optional<Customer> customer = customerRepositoryl.findById(id);

        if(customer.isPresent()){
            return  customer.get();
        }else{
            return new Customer();
        }

    }

//    @Override
    public List<Customer> findAll(){
        return customerRepositoryl.findAll();
    }

}
