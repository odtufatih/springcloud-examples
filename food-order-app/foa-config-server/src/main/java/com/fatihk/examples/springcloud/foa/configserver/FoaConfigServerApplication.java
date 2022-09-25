package com.fatihk.examples.springcloud.foa.configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class FoaConfigServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FoaConfigServerApplication.class, args);
    }

}
