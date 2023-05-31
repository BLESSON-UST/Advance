package com.javatechie.controller;

import com.javatechie.dto.Customer;
import com.javatechie.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customersInfo")
public class CustomerController {

    @Autowired
    private CustomerService service;

    @PostMapping
    public Customer addCustomer(@RequestBody Customer customer){
        return service.addCustomer(customer);
    }

    @GetMapping
    public List<Customer> getCustomers(){
        return service.getCustomers();
    }

    @GetMapping("/{id}")
    public Customer getCustomer(@PathVariable int id){
        return service.getCustomer(id);
    }

}
