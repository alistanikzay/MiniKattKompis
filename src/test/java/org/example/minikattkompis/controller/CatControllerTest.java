package org.example.minikattkompis.controller;

import org.example.minikattkompis.model.Cat;
import org.example.minikattkompis.service.CatService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class CatControllerTest {

    @Mock
    private CatService catService;

    @Mock
    private Model model;

    @InjectMocks
    private CatController catController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(catController).build();
    }

    @Test
    void index_ShouldReturnIndexView() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    void addCatForm_ShouldAddEmptyCatToModelAndReturnView() throws Exception {
        mockMvc.perform(get("/cats/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("add-cat"))
                .andExpect(model().attributeExists("cat"));
    }

    @Test
    void saveCat_ShouldCallServiceAndRedirect() throws Exception {
        mockMvc.perform(post("/cats/add")
                        .param("name", "Misse")
                        .param("age", "2"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/cats"));

        verify(catService, times(1)).addCat(any(Cat.class));
    }

    @Test
    void catDetail_ShouldAddCatToModelAndReturnView() throws Exception {
        Cat cat = new Cat("Misse", 2, null);
        when(catService.getCat(1L)).thenReturn(cat);

        mockMvc.perform(get("/cats/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("cat-detail"))
                .andExpect(model().attributeExists("cat"));

        verify(catService, times(1)).getCat(1L);
    }
}
