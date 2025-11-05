package org.example.minikattkompis.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class WeatherService {

    @Cacheable("weather")
    public Map<String, String> getWeather(String city) {
        // Simulerar hämtning från ett externt API
        return Map.of(
                "city", city,
                "forecast", "Soligt",
                "temperature", "18°C",
                "updated", LocalDateTime.now().toString()
        );
    }
}
