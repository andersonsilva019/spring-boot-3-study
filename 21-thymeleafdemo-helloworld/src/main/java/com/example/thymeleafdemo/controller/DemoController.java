package com.example.thymeleafdemo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {

    @Value("${welcome.message}")
    private String message;

    @GetMapping("/hello")
    public String sayHello(Model  model){
        model.addAttribute("message", message);

        return "helloworld"; // view
    }
}
