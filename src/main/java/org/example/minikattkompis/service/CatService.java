package org.example.minikattkompis.service;

import org.example.minikattkompis.model.Cat;
import org.example.minikattkompis.model.Reminder;
import org.example.minikattkompis.repository.CatRepository;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CatService {

    private final CatRepository repo;
    private final CatNameService nameService;

    public CatService(@NonNull CatRepository repo, @NonNull CatNameService nameService) {
        this.repo = repo;
        this.nameService = nameService;
    }

    // --------------------
    // Alla katter
    // --------------------
    @Cacheable("cats")
    public @NonNull List<Cat> getAllCats() {
        System.out.println("Fetching cats from DB...");
        return repo.findAll();
    }

    // --------------------
    // Lägg till katt
    // --------------------
    @CacheEvict(value = "cats", allEntries = true)
    public @NonNull Cat addCat(@NonNull Cat cat) {
        cat.setName(nameService.safeCatName(cat.getName()));
        return repo.save(cat);
    }

    // --------------------
    // Hämta en specifik katt
    // --------------------
    @Cacheable(value = "cats", key = "#id")
    public @Nullable Cat getCat(@NonNull Long id) {
        System.out.println("Fetching cat " + id + " from DB...");
        return repo.findById(id).orElse(null);
    }

    // --------------------
    // Påminnelser om veterinärbesök
    // --------------------
    @Cacheable("upcomingVetVisits")
    public @NonNull List<Reminder> getUpcomingVetVisits() {
        System.out.println("Fetching upcoming vet visits from DB...");
        return repo.findAll().stream()
                .filter(c -> c.getNextVetVisit() != null &&
                        c.getNextVetVisit().isBefore(LocalDate.now().plusDays(7)))
                .map(c -> new Reminder(
                        c.getId(),
                        c.getName(),
                        "Veterinärbesök",
                        c.getNextVetVisit()
                ))
                .collect(Collectors.toList());
    }
}
