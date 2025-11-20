package org.example.minikattkompis.service;

import org.example.minikattkompis.controller.ReminderGraphQLController;
import org.example.minikattkompis.model.Reminder;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class ReminderGraphQLControllerTest {

    @Test
    void allReminders_ShouldReturnListFromCatService() {
        // Arrange
        CatService catService = Mockito.mock(CatService.class);
        ReminderGraphQLController controller = new ReminderGraphQLController(catService);

        Reminder reminder = new Reminder(1L, "Luna", "Veterinärbesök", LocalDate.now().plusDays(1));
        when(catService.getUpcomingVetVisits()).thenReturn(List.of(reminder));

        // Act
        var result = controller.allReminders();

        // Assert
        assertThat(result)
                .isNotNull()
                .hasSize(1)
                .extracting(Reminder::getCatName)
                .containsExactly("Luna");
    }
}
