package org.example.minikattkompis.controller;

import org.example.minikattkompis.model.Cat;
import org.example.minikattkompis.service.CatService;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.graphql.data.method.annotation.QueryMapping;

import java.util.List;

@Controller
public class CatGraphQLController {

    private final CatService catService;

    public CatGraphQLController(@NonNull CatService catService) {
        this.catService = catService;
    }

    @QueryMapping
    public @NonNull List<Cat> cats() {
        return catService.getAllCats();
    }
}
