package org.example.minikattkompis.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WeatherService {

    @Value("${openweather.apikey}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    @SuppressWarnings("unchecked")
    @Cacheable("weather")
    public Map<String, Object> getWeather(String city) {
        // Enkel URL-sträng
        String url = "https://api.openweathermap.org/data/2.5/weather?q="
                + city
                + "&units=metric&lang=sv&appid="
                + apiKey;

        Map<String, Object> response = restTemplate.getForObject(url, Map.class);
        if (response == null) return Map.of();

        // Extrahera relevanta fält för frontend
        Map<String, Object> main = (Map<String, Object>) response.get("main");
        List<Map<String, Object>> weatherList = (List<Map<String, Object>>) response.get("weather");

        Map<String, Object> result = new HashMap<>();
        result.put("city", response.get("name"));
        result.put("temperature", main != null ? main.get("temp") : null);
        result.put("humidity", main != null ? main.get("humidity") : null);
        result.put("condition", (weatherList != null && !weatherList.isEmpty())
                ? weatherList.get(0).get("description")
                : null);

        return result;
    }
}
