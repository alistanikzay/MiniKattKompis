package org.example.minikattkompis.config;

import org.example.minikattkompis.mapper.CatMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ConfigTest {

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private CatMapper catMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ApiVersionConfig apiVersionConfig;

    @Test
    void contextLoads() {
        assertThat(apiVersionConfig).isNotNull();
        assertThat(cacheManager).isNotNull();
        assertThat(catMapper).isNotNull();
        assertThat(restTemplate).isNotNull();
    }

    @Test
    void cacheManagerHasWeatherCache() {
        assertThat(cacheManager.getCache("weather")).isNotNull();
    }
}
