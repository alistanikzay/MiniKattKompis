package org.example.minikattkompis.controller;

import org.example.minikattkompis.service.WeatherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/weather")
    public String showWeather(@RequestParam(defaultValue = "Stockholm") String city, Model model) {
        model.addAttribute("weather", weatherService.getWeather(city));
        return "weather";
    }
}
