package org.example.minikattkompis.controller;

import org.example.minikattkompis.model.Recommendation;
import org.example.minikattkompis.service.CatRecommendationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class RecommendationRestControllerTest {

    private MockMvc mockMvc;
    private CatRecommendationService service;

    @BeforeEach
    void setUp() {
        service = Mockito.mock(CatRecommendationService.class);
        RecommendationRestController controller = new RecommendationRestController(service);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void getAllRecommendations_ShouldReturnJsonList() throws Exception {
        // Arrange
        Recommendation rec = new Recommendation(1L, "Test", "basic");
        when(service.getAllRecommendations()).thenReturn(List.of(rec));

        // Act & Assert
        mockMvc.perform(get("/api/recommendations").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].text").value("Test"))
                .andExpect(jsonPath("$[0].category").value("basic"));
    }

    @Test
    void getPremiumRecommendations_ShouldReturnJsonPremiumList() throws Exception {
        // Arrange
        Recommendation rec = new Recommendation(2L, "Premium tips", "premium");
        when(service.getPremiumRecommendations()).thenReturn(List.of(rec));

        // Act & Assert
        mockMvc.perform(get("/api/recommendations/premium").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].text").value("Premium tips"))
                .andExpect(jsonPath("$[0].category").value("premium"));
    }
}
