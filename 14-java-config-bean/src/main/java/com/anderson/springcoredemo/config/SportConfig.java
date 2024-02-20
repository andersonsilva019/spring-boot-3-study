package com.anderson.springcoredemo.config;

import com.anderson.springcoredemo.common.Coach;
import com.anderson.springcoredemo.common.SwimCoach;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SportConfig {

    @Bean("aquaticCoach")
    public Coach swimCoach(){
        return new SwimCoach();
    }
}
