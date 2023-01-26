package com.hotelmanagement.user.service.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class MyConfig {

    @Bean
    @LoadBalanced    // service registry will balance multiple request using client service-name
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
