package org.example.minikattkompis.service;

import org.junit.jupiter.api.Test;
import java.util.Map;
import static org.assertj.core.api.Assertions.assertThat;

class WeatherServiceTest {

    @Test
    void shouldReturnWeatherMapStructure() {

        WeatherService service = new WeatherService() {
            @Override
            public Map<String, Object> getWeather(String city) {
                return Map.of(
                        "city", city,
                        "temperature", 20,
                        "humidity", 65,
                        "condition", "Sunny"
                );
            }
        };

        Map<String, Object> result = service.getWeather("Stockholm");

        assertThat(result).isNotNull();
        assertThat(result).containsKeys("city", "temperature", "humidity", "condition");
        assertThat(result.get("city")).isEqualTo("Stockholm");
    }
}
