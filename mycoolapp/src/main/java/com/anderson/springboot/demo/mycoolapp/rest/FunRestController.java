package com.anderson.springboot.demo.mycoolapp.rest;

import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class FunRestController {
    @GetMapping
    public String sayHello(){
        return "Hello World!";
    }

    @GetMapping("/your-name/{name}")
    public String sayYourName(@PathVariable String name){
        return name;
    }
}
