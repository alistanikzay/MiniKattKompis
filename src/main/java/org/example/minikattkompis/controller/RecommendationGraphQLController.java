package org.example.minikattkompis.controller;

import org.example.minikattkompis.model.Recommendation;
import org.example.minikattkompis.service.CatRecommendationService;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class RecommendationGraphQLController {

    private final CatRecommendationService recommendationService;

    public RecommendationGraphQLController(CatRecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    @QueryMapping
    public List<Recommendation> allRecommendations() {
        return recommendationService.getAllRecommendations();
    }

    @QueryMapping
    public List<Recommendation> premiumRecommendations() {
        return recommendationService.getPremiumRecommendations();
    }
}
