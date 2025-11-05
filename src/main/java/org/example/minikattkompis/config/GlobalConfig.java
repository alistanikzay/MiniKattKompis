package org.example.minikattkompis.config;

import org.example.minikattkompis.mapper.CatMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GlobalConfig {

    @Bean
    public CatMapper catMapper() {
        return new CatMapper();
    }
}
