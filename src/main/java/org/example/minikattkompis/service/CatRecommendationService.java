package org.example.minikattkompis.service;

import org.example.minikattkompis.model.Cat;
import org.example.minikattkompis.model.Recommendation;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CatRecommendationService {

    /**
     * Returnerar rekommendationer för en specifik katt.
     * - Alla katter får Catnip Treats
     * - Unga katter (<2 år) får Interactive Toy
     * - Äldre katter (>=2 år) får Comfort Bed
     */
    public List<Recommendation> getRecommendations(Cat cat) {
        List<Recommendation> recs = new ArrayList<>();
        long id = 1;

        // Alla katter får Catnip Treats
        recs.add(new Recommendation(id++, "Catnip Treats", "Food"));

        // Unga katter < 2 år
        if (cat.getAge() < 2) {
            recs.add(new Recommendation(id++, "Interactive Toy", "Toy"));
        } else {
            recs.add(new Recommendation(id++, "Comfort Bed", "Accessory"));
        }

        return recs;
    }

    /**
     * Returnerar alla standardrekommendationer
     */
    public List<Recommendation> getAllRecommendations() {
        List<Recommendation> recs = new ArrayList<>();
        recs.add(new Recommendation(1L, "Interactive Toy", "Toy"));
        recs.add(new Recommendation(2L, "Comfort Bed", "Accessory"));
        recs.add(new Recommendation(3L, "Catnip Treats", "Food"));
        return recs;
    }

    /**
     * Returnerar premiumrekommendationer
     */
    public List<Recommendation> getPremiumRecommendations() {
        List<Recommendation> recs = new ArrayList<>();
        recs.add(new Recommendation(4L, "Luxury Cat Tower", "Premium Toy"));
        recs.add(new Recommendation(5L, "Special Diet Cat Food", "Premium Food"));
        return recs;
    }
}
