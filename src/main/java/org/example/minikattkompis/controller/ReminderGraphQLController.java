package org.example.minikattkompis.controller;

import org.example.minikattkompis.model.Reminder;
import org.example.minikattkompis.service.CatService;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ReminderGraphQLController {

    private final CatService catService;

    public ReminderGraphQLController(CatService catService) {
        this.catService = catService;
    }

    @QueryMapping
    public List<Reminder> allReminders() {
        return catService.getUpcomingVetVisits();
    }
}
