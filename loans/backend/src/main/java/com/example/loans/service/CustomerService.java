package com.example.loans.service;

import com.example.loans.models.Customer;

public interface CustomerService {
    Customer save(Customer customer);

    Customer findByCpf(String cpf);
}
