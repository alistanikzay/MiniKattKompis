package org.example.minikattkompis.service;

import org.example.minikattkompis.model.Cat;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CatRecommendationService {

    public List<String> getRecommendations(Cat cat) {
        List<String> recs = new ArrayList<>();
        if(cat.getAge() < 2) recs.add("Interactive Toy");
        if(cat.getAge() >= 2) recs.add("Comfort Bed");
        recs.add("Catnip Treats");
        return recs;
    }
}
