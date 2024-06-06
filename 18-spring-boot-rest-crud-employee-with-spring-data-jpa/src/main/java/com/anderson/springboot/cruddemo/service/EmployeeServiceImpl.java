package com.anderson.springboot.cruddemo.service;

import com.anderson.springboot.cruddemo.entity.Employee;
import com.anderson.springboot.cruddemo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> findAll() {
        return this.employeeRepository.findAll();
    }

    @Override
    public Employee findById(int theId) {
        Optional<Employee> result = this.employeeRepository.findById(theId);

        if(result.isPresent()){
            return result.get();
        }else {
            throw new RuntimeException("Did not find employee id - " + theId);
        }
    }

    @Override
    public Employee save(Employee theEmployee) {
        return this.employeeRepository.save(theEmployee);
    }

    @Override
    public void deleteById(int theId) {
        this.employeeRepository.deleteById(theId);
    }
}
