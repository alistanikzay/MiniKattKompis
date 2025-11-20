package org.example.minikattkompis.service;

import org.example.minikattkompis.model.Cat;
import org.example.minikattkompis.model.Reminder;
import org.example.minikattkompis.repository.CatRepository;
import org.jspecify.annotations.NonNull;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReminderService {

    private final CatRepository catRepository;

    public ReminderService(@NonNull CatRepository catRepository) {
        this.catRepository = catRepository;
    }

    /**
     * Returnerar påminnelser för katter som har veterinärbesök inom de kommande 7 dagarna.
     */
    @Cacheable("upcomingVetVisits")
    public @NonNull List<Reminder> getUpcomingVetVisits() {
        LocalDate today = LocalDate.now();
        LocalDate limit = today.plusDays(7);

        return catRepository.findAll().stream()
                .filter(cat -> cat.getNextVetVisit() != null)
                .filter(cat -> !cat.getNextVetVisit().isBefore(today))
                .filter(cat -> !cat.getNextVetVisit().isAfter(limit))
                .map(cat -> new Reminder(
                        cat.getId(),
                        cat.getName(),
                        "Veterinärbesök",
                        cat.getNextVetVisit()
                ))
                .collect(Collectors.toList());
    }
}
