package com.anderson.springboot.cruddemo.controller;

import com.anderson.springboot.cruddemo.entity.Employee;
import com.anderson.springboot.cruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public List<Employee> getAllEmployees(){
        return this.employeeService.findAll();
    }

    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable(name = "employeeId") int employeeId){
        Employee theEmployee = this.employeeService.findById(employeeId);

        if(theEmployee == null){
            throw new RuntimeException("Employee id not found - " +employeeId);
        }

        return theEmployee;
    }

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee theEmployee){

        theEmployee.setId(0);

        return this.employeeService.save(theEmployee);
    }

    @PutMapping("/employees/{employeeId}")
    public Employee updateEmployee(
            @PathVariable(name = "employeeId") int employeeId,
            @RequestBody Employee theEmployee
    ){

        theEmployee.setId(employeeId);
        return this.employeeService.save(theEmployee);
    }

    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable(name = "employeeId") int employeeId){
        Employee employeeAlreadyExists = employeeService.findById(employeeId);

        if(employeeAlreadyExists == null){
            throw new RuntimeException("Employee with id " + employeeId + " not found");
        }

        this.employeeService.deleteById(employeeId);

        return "Deleted employee with id " + employeeId;
    }

}
