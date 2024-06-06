package com.example.loans.service;

import com.example.loans.models.Customer;
import com.example.loans.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class LoansServiceImpl implements LoansService{

    private CustomerRepository customerRepository;

    @Autowired
    public LoansServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer loansForTheCustomer(String cpf) {
        Optional<Customer> customer = this.customerRepository.findByCpf(cpf);

        if(customer.isPresent()){
            return customer.get();
        }

        return null;
    }
}
