package com.example.loans.service;

import com.example.loans.models.Customer;
import com.example.loans.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer save(Customer customer) {
        return this.customerRepository.save(customer);
    }

    @Override
    public Customer findByCpf(String cpf) {

        Optional<Customer> customer = this.customerRepository.findByCpf(cpf);

        if(customer.isPresent()){
            return customer.get();
        }else {
            throw new RuntimeException("Customer with CPF " + cpf + " not found");
        }
    }
}
