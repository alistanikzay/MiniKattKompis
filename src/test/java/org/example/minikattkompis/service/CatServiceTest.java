package org.example.minikattkompis.service;

import org.example.minikattkompis.model.Cat;
import org.example.minikattkompis.repository.CatRepository;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class CatServiceTest {

    @Test
    void addCat_shouldUseSafeNameAndSave() {
        CatRepository repo = mock(CatRepository.class);
        CatNameService nameService = mock(CatNameService.class);
        CatService service = new CatService(repo, nameService);

        Cat cat = new Cat(null, 3, "Boll");
        when(nameService.safeCatName(null)).thenReturn("Okänd katt");
        when(repo.save(any(Cat.class))).thenAnswer(i -> i.getArgument(0));

        Cat saved = service.addCat(cat);

        assertThat(saved.getName()).isEqualTo("Okänd katt");
        verify(repo).save(any(Cat.class));
    }
}
