package org.example.minikattkompis.controller;

import org.example.minikattkompis.service.WeatherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

class WeatherControllerTest {

    private WeatherService weatherService;
    private WeatherController weatherController;

    @BeforeEach
    void setUp() {
        weatherService = mock(WeatherService.class);
        weatherController = new WeatherController(weatherService);
    }

    @Test
    void showWeather_DefaultCity() {
        Map<String, Object> weatherMap = new HashMap<>();
        weatherMap.put("temperature", "20°C");
        weatherMap.put("condition", "Sunny");

        when(weatherService.getWeather("Stockholm")).thenReturn(weatherMap);

        Model model = new ConcurrentModel();
        String view = weatherController.showWeather("Stockholm", model);

        assertThat(view).isEqualTo("weather");
        assertThat(model.getAttribute("weather")).isEqualTo(weatherMap);

        verify(weatherService, times(1)).getWeather("Stockholm");
    }

    @Test
    void showWeather_GivenCity() {
        Map<String, Object> weatherMap = new HashMap<>();
        weatherMap.put("temperature", "15°C");
        weatherMap.put("condition", "Rainy");

        when(weatherService.getWeather("Gothenburg")).thenReturn(weatherMap);

        Model model = new ConcurrentModel();
        String view = weatherController.showWeather("Gothenburg", model);

        assertThat(view).isEqualTo("weather");
        assertThat(model.getAttribute("weather")).isEqualTo(weatherMap);

        verify(weatherService, times(1)).getWeather("Gothenburg");
    }
}
