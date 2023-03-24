package com.rewedigital.medicalrecord.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.util.UriComponentsBuilder;

@Configuration
public class AppConfig {

    @Bean
    UriComponentsBuilder create() {
        return UriComponentsBuilder.newInstance();
    }

}
