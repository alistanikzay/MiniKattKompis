package org.example.minikattkompis.service;

import org.example.minikattkompis.model.Cat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CatRecommendationServiceTest {

    private CatRecommendationService recommendationService;

    @BeforeEach
    void setUp() {
        recommendationService = new CatRecommendationService();
    }

    @Test
    void getRecommendations_ShouldReturnInteractiveToyForYoungCat() {
        // Arrange
        Cat kitten = new Cat("Misse", 1, "Boll");

        // Act
        List<String> recommendations = recommendationService.getRecommendations(kitten);

        // Assert
        assertThat(recommendations)
                .isNotNull()
                .contains("Interactive Toy", "Catnip Treats")
                .doesNotContain("Comfort Bed");
    }

    @Test
    void getRecommendations_ShouldReturnComfortBedForOlderCat() {
        // Arrange
        Cat adult = new Cat("Luna", 3, "Fjäderleksak");

        // Act
        List<String> recommendations = recommendationService.getRecommendations(adult);

        // Assert
        assertThat(recommendations)
                .isNotNull()
                .contains("Comfort Bed", "Catnip Treats")
                .doesNotContain("Interactive Toy");
    }

    @Test
    void getRecommendations_ShouldAlwaysIncludeCatnipTreats() {
        // Arrange
        Cat anyCat = new Cat("Simba", 5, "Laserpekare");

        // Act
        List<String> recommendations = recommendationService.getRecommendations(anyCat);

        // Assert
        assertThat(recommendations)
                .isNotEmpty()
                .contains("Catnip Treats");
    }
}
