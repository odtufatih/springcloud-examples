package com.fatihk.examples.springcloud.foa.common.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "com.fatihk.examples.springcloud.foa.common.client")
public class FeignConfig {

}
