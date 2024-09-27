package com.example.helloworld_swagger.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class TipoCambioRangoMonedaConfigMain {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
