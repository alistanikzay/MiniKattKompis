package org.example.minikattkompis.service;

import org.example.minikattkompis.model.Cat;
import org.example.minikattkompis.model.Recommendation;
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
        List<Recommendation> recommendations = recommendationService.getRecommendations(kitten);

        // Assert
        assertThat(recommendations)
                .isNotNull()
                .extracting(Recommendation::getText) // extrahera texten för jämförelse
                .contains("Interactive Toy", "Catnip Treats")
                .doesNotContain("Comfort Bed");
    }

    @Test
    void getRecommendations_ShouldReturnComfortBedForOlderCat() {
        // Arrange
        Cat adult = new Cat("Luna", 3, "Fjäderleksak");

        // Act
        List<Recommendation> recommendations = recommendationService.getRecommendations(adult);

        // Assert
        assertThat(recommendations)
                .isNotNull()
                .extracting(Recommendation::getText)
                .contains("Comfort Bed", "Catnip Treats")
                .doesNotContain("Interactive Toy");
    }

    @Test
    void getRecommendations_ShouldAlwaysIncludeCatnipTreats() {
        // Arrange
        Cat anyCat = new Cat("Simba", 5, "Laserpekare");

        // Act
        List<Recommendation> recommendations = recommendationService.getRecommendations(anyCat);

        // Assert
        assertThat(recommendations)
                .isNotEmpty()
                .extracting(Recommendation::getText)
                .contains("Catnip Treats");
    }

    @Test
    void getAllRecommendations_ShouldReturnAll() {
        // Act
        List<Recommendation> allRecs = recommendationService.getAllRecommendations();

        // Assert
        assertThat(allRecs)
                .isNotNull()
                .extracting(Recommendation::getText)
                .containsExactly("Interactive Toy", "Comfort Bed", "Catnip Treats");
    }

    @Test
    void getPremiumRecommendations_ShouldReturnPremiumOnly() {
        // Act
        List<Recommendation> premiumRecs = recommendationService.getPremiumRecommendations();

        // Assert
        assertThat(premiumRecs)
                .isNotNull()
                .extracting(Recommendation::getText)
                .containsExactly("Luxury Cat Tower", "Special Diet Cat Food");
    }
}
