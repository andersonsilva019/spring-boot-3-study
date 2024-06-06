package com.example.loans.service;

import com.example.loans.models.Customer;

import java.math.BigDecimal;

public interface LoansService {
    Customer loansForTheCustomer(String cpf);
}
