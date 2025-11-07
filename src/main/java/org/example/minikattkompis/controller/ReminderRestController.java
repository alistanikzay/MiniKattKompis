package org.example.minikattkompis.controller;

import org.example.minikattkompis.model.Reminder;
import org.example.minikattkompis.service.CatService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reminders")
public class ReminderRestController {

    private final CatService catService;

    public ReminderRestController(CatService catService) {
        this.catService = catService;
    }

    @GetMapping
    public List<Reminder> getAllReminders() {
        return catService.getUpcomingVetVisits();
    }
}
