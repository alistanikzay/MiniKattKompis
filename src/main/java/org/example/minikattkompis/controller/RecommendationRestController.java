package org.example.minikattkompis.controller;

import org.example.minikattkompis.model.Recommendation;
import org.example.minikattkompis.service.CatRecommendationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recommendations")
public class RecommendationRestController {

    private final CatRecommendationService recommendationService;

    public RecommendationRestController(CatRecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    // Alla rekommendationer
    @GetMapping
    public List<Recommendation> getAllRecommendations() {
        return recommendationService.getAllRecommendations();
    }

    // PREMIUM: personliga AI-rekommendationer
    @GetMapping("/premium")
    public List<Recommendation> getPremiumRecommendations() {
        return recommendationService.getPremiumRecommendations();
    }
}
