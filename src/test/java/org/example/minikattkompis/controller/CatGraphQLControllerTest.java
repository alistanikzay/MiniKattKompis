package org.example.minikattkompis.controller;

import org.example.minikattkompis.model.Cat;
import org.example.minikattkompis.service.CatService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

class CatGraphQLControllerTest {

    private CatService catService;
    private CatGraphQLController catGraphQLController;

    @BeforeEach
    void setUp() {
        catService = mock(CatService.class);
        catGraphQLController = new CatGraphQLController(catService);
    }

    @Test
    void cats_ShouldReturnAllCats() {
        // Arrange
        List<Cat> mockCats = Arrays.asList(
                new Cat("Whiskers", 3, "Boll"),
                new Cat("Luna", 2, "Fjäderleksak")
        );
        when(catService.getAllCats()).thenReturn(mockCats);

        // Act
        List<Cat> result = catGraphQLController.cats();

        // Assert
        assertThat(result)
                .isNotNull()
                .hasSize(2)
                .extracting(Cat::getName)
                .containsExactly("Whiskers", "Luna");

        verify(catService, times(1)).getAllCats();
    }
}
