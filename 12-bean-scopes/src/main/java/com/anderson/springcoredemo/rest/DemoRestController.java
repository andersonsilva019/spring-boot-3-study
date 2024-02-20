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
    private Coach anotherCoach;

    @Autowired
    public DemoRestController(
            @Qualifier("tennisCoach") Coach coach,
            @Qualifier("tennisCoach") Coach anotherCoach
    ){
        System.out.println("In constructor" + getClass().getSimpleName());
        this.coach = coach;
        this.anotherCoach = anotherCoach;
    }

    @GetMapping("/dailyworkout")
    public String getDailyWorkout(){
        return this.coach.getDailyWorkout();
    }

    @GetMapping("/check")
    public String check(){
        return "Comparing beans: coach == anotherCoach " + (this.coach == this.anotherCoach);
    }
}
