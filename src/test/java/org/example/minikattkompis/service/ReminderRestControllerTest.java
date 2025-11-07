package org.example.minikattkompis.service;

import org.example.minikattkompis.controller.ReminderRestController;
import org.example.minikattkompis.model.Reminder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ReminderRestControllerTest {

    private MockMvc mockMvc;
    private CatService catService;

    @BeforeEach
    void setUp() {
        catService = Mockito.mock(CatService.class);
        ReminderRestController controller = new ReminderRestController(catService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void getReminders_ShouldReturnJsonListOfReminders() throws Exception {
        // Arrange
        Reminder reminder = new Reminder(
                1L,
                "Misse",
                "Veterinärbesök",
                LocalDate.now().plusDays(2)
        );

        when(catService.getUpcomingVetVisits())
                .thenReturn(List.of(reminder));

        // Act & Assert
        mockMvc.perform(get("/api/reminders").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].catName").value("Misse"))
                .andExpect(jsonPath("$[0].description").value("Veterinärbesök"))
                .andExpect(jsonPath("$[0].date").value(reminder.getDate().toString()));
    }
}
