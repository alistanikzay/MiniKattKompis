package org.example.minikattkompis.controller;

import org.example.minikattkompis.service.WeatherService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/weather")
public class WeatherRestController {

    private final WeatherService weatherService;

    public WeatherRestController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping
    public Map<String, Object> getWeather(@RequestParam(defaultValue = "Stockholm") String city) {
        return weatherService.getWeather(city);
    }
}
