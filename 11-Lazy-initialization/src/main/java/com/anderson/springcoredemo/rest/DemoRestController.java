package com.anderson.springcoredemo.rest;

import com.anderson.springcoredemo.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoRestController {
    // define a private field for the dependency
    private Coach coach;

    @Autowired
    public DemoRestController(@Qualifier("tennisCoach") Coach coach){
        System.out.println("In constructor" + getClass().getSimpleName());
        this.coach = coach;
    }

    @GetMapping("/dailyworkout")
    public String getDailyWorkout(){
        return this.coach.getDailyWorkout();
    }
}
