package com.anderson.restcontroller.rest;

import com.anderson.restcontroller.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private List<Student> students;

    @PostConstruct
    public void loadData(){
        this.students = new ArrayList<>();
        this.students.add(new Student("Anderson", "Souza"));
        this.students.add(new Student("John", "Doe"));
        this.students.add(new Student("Mary", "Smith"));
    }

    @GetMapping("/students")
    public List<Student> getStudents(){
        return this.students;
    }

    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId){

        if((studentId >= this.students.size()) || (studentId < 0)){
            throw new StudentNotFoundException("Student id not found " +studentId);
        }

        return this.students.get(studentId);
    }

}
