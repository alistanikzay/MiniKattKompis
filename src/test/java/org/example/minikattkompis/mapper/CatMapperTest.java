package org.example.minikattkompis.mapper;

import org.example.minikattkompis.dto.CatDTO;
import org.example.minikattkompis.model.Cat;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CatMapperTest {

    private final CatMapper mapper = new CatMapper();

    @Test
    void shouldMapEntityToDto() {
        Cat cat = new Cat("Whiskers", 3, "Boll");
        CatDTO dto = mapper.toDTO(cat);

        assertThat(dto.getName()).isEqualTo("Whiskers");
        assertThat(dto.getFavoriteToy()).isEqualTo("Boll");
    }

    @Test
    void shouldMapDtoToEntity() {
        CatDTO dto = new CatDTO(1L, "Luna", 2, "Fjärderleksak");
        Cat entity = mapper.toEntity(dto);

        assertThat(entity.getName()).isEqualTo("Luna");
        assertThat(entity.getFavoriteToy()).isEqualTo("Fjärderleksak");
    }
}
