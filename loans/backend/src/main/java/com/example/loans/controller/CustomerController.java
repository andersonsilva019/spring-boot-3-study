package com.example.loans.controller;

import com.example.loans.enumeration.LoansType;
import com.example.loans.models.Customer;
import com.example.loans.models.Loans;
import com.example.loans.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    @PostMapping("/customers")
    public Customer createCustomer(@RequestBody Customer customer){
        return this.customerService.save(customer);
    }

//    @GetMapping("/customers/{customerCpf}")
//    public ResponseEntity<Object> findByCpf(@PathVariable(name = "customerCpf") String customerCpf){
//
//        Map<String, Object> map = new HashMap<String, Object>();
//        List<Loans> loansList = new ArrayList<Loans>();
//
//        loansList.add(new Loans(LoansType.PERSONAL, (float) 0.4));
//
//        map.put("customer", "teste");
//        map.put("loans", loansList);
//
//        return new ResponseEntity<Object>(map, HttpStatus.CREATED);
//    }
}
