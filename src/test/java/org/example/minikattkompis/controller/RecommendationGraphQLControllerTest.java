package org.example.minikattkompis.controller;

import org.example.minikattkompis.model.Recommendation;
import org.example.minikattkompis.service.CatRecommendationService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class RecommendationGraphQLControllerTest {

    @Test
    void allRecommendations_ShouldReturnListFromService() {
        // Arrange
        CatRecommendationService service = Mockito.mock(CatRecommendationService.class);
        RecommendationGraphQLController controller = new RecommendationGraphQLController(service);

        Recommendation rec = new Recommendation("Luna", "Testrekommendation");
        when(service.getAllRecommendations()).thenReturn(List.of(rec));

        // Act
        var result = controller.allRecommendations();

        // Assert
        assertThat(result)
                .isNotNull()
                .hasSize(1)
                .extracting(Recommendation::getCatName)
                .containsExactly("Luna");
    }

    @Test
    void premiumRecommendations_ShouldReturnPremiumListFromService() {
        // Arrange
        CatRecommendationService service = Mockito.mock(CatRecommendationService.class);
        RecommendationGraphQLController controller = new RecommendationGraphQLController(service);

        Recommendation rec = new Recommendation("Mittens", "Premium tips");
        when(service.getPremiumRecommendations()).thenReturn(List.of(rec));

        // Act
        var result = controller.premiumRecommendations();

        // Assert
        assertThat(result)
                .isNotNull()
                .hasSize(1)
                .extracting(Recommendation::getCatName)
                .containsExactly("Mittens");
    }
}
