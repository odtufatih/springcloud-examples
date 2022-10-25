package com.fatihk.examples.springcloud.foa.order.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.fatihk.examples.springcloud.foa.common") // this is required for EnableFeignClients annotation in foa-common project no be processed
public class CommonConfig {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }


}
