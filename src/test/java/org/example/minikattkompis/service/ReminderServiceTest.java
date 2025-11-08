package org.example.minikattkompis.service;

import org.example.minikattkompis.model.Cat;
import org.example.minikattkompis.model.Reminder;
import org.example.minikattkompis.repository.CatRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class ReminderServiceTest {

    private CatRepository repo;
    private CatService catService;

    @BeforeEach
    void setUp() {
        repo = mock(CatRepository.class);
        catService = new CatService(repo, mock(CatNameService.class));
    }

    @Test
    void getUpcomingVetVisits_ShouldReturnRemindersForCatsWithVetVisitWithin7Days() {
        // Arrange
        Cat cat1 = new Cat("Whiskers", 3, "Boll");
        cat1.setNextVetVisit(LocalDate.now().plusDays(3));

        Cat cat2 = new Cat("Luna", 2, "Fjäderleksak");
        cat2.setNextVetVisit(LocalDate.now().plusDays(10)); // för långt bort

        when(repo.findAll()).thenReturn(List.of(cat1, cat2));

        // Act
        List<Reminder> result = catService.getUpcomingVetVisits();

        // Assert
        assertThat(result)
                .hasSize(1)
                .extracting(Reminder::getCatName)
                .containsExactly("Whiskers");
    }

    @Test
    void getUpcomingVetVisits_ShouldReturnEmptyListWhenNoVisitsWithin7Days() {
        // Arrange
        Cat cat = new Cat("Mittens", 5, "Solsäng");
        cat.setNextVetVisit(LocalDate.now().plusDays(14));
        when(repo.findAll()).thenReturn(List.of(cat));

        // Act
        List<Reminder> result = catService.getUpcomingVetVisits();

        // Assert
        assertThat(result).isEmpty();
    }
}
