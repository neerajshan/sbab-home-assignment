package com.neeraj.test.demoproject.integrationTest;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


@Component
public class component {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
