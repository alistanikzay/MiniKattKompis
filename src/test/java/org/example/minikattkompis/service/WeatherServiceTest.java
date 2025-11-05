package org.example.minikattkompis.service;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class WeatherServiceTest {

    @Test
    void shouldReturnWeatherMap() {
        WeatherService service = new WeatherService();
        Map<String, String> result = service.getWeather("Stockholm");

        assertThat(result).containsKeys("city", "forecast", "temperature");
        assertThat(result.get("city")).isEqualTo("Stockholm");
    }
}
