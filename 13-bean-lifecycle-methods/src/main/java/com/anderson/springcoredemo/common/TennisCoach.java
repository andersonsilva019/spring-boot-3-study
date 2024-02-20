package com.anderson.springcoredemo.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;
@Component
public class TennisCoach implements Coach{

    public TennisCoach(){
        System.out.println("In constructor" + getClass().getSimpleName());
    }

    // define our init method
    @PostConstruct
    public void someMethodInit(){
        System.out.println("Init");
    }

    @PreDestroy
    public void someMethodDestroy(){
        System.out.println("Destroy");
    }

    // define our destroy method
    @Override
    public String getDailyWorkout() {
        return "Tennis Coach";
    }
}
  