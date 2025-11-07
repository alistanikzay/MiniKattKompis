package org.example.minikattkompis.service;

import org.example.minikattkompis.model.Cat;
import org.example.minikattkompis.model.Recommendation;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CatRecommendationService {

    // Befintlig metod för HTML-vyn (kan behållas men ändras till Recommendation)
    public List<Recommendation> getRecommendations(Cat cat) {
        List<Recommendation> recs = new ArrayList<>();
        long id = 1;
        if(cat.getAge() < 2) recs.add(new Recommendation(id++, "Interactive Toy", "Toy"));
        if(cat.getAge() >= 2) recs.add(new Recommendation(id++, "Comfort Bed", "Accessory"));
        recs.add(new Recommendation(id++, "Catnip Treats", "Food"));
        return recs;
    }

    // REST / GraphQL: alla rekommendationer
    public List<Recommendation> getAllRecommendations() {
        List<Recommendation> recs = new ArrayList<>();
        recs.add(new Recommendation(1L, "Interactive Toy", "Toy"));
        recs.add(new Recommendation(2L, "Comfort Bed", "Accessory"));
        recs.add(new Recommendation(3L, "Catnip Treats", "Food"));
        return recs;
    }

    // REST / GraphQL: premiumrekommendationer
    public List<Recommendation> getPremiumRecommendations() {
        List<Recommendation> recs = new ArrayList<>();
        recs.add(new Recommendation(4L, "Luxury Cat Tower", "Premium Toy"));
        recs.add(new Recommendation(5L, "Special Diet Cat Food", "Premium Food"));
        return recs;
    }
}
