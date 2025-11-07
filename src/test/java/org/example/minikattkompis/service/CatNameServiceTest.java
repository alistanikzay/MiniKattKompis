package org.example.minikattkompis.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CatNameServiceTest {

    private CatNameService catNameService;

    @BeforeEach
    void setUp() {
        catNameService = new CatNameService();
    }

    @Test
    void safeCatName_ShouldReturnSameName_WhenNameIsValid() {
        String result = catNameService.safeCatName("Misse");
        assertThat(result).isEqualTo("Misse");
    }

    @Test
    void safeCatName_ShouldReturnOkandKatt_WhenNameIsNull() {
        String result = catNameService.safeCatName(null);
        assertThat(result).isEqualTo("Okänd katt");
    }

    @Test
    void safeCatName_ShouldReturnOkandKatt_WhenNameIsEmpty() {
        String result = catNameService.safeCatName("");
        assertThat(result).isEqualTo("Okänd katt");
    }

    @Test
    void safeCatName_ShouldReturnOkandKatt_WhenNameIsBlank() {
        String result = catNameService.safeCatName("   ");
        assertThat(result).isEqualTo("Okänd katt");
    }
}
