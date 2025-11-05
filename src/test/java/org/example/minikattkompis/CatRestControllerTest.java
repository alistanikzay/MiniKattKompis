package org.example.minikattkompis;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.client.RestTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class CatRestControllerTest {

    @Test
    void shouldReturnOkStatus() {
        RestTestClient client = RestTestClient.bindToServer("http://localhost:8080");

        client.get()
                .uri("/api/cats/all")
                .header("Version", "2")
                .exchange()
                .expectStatus()
                .isOk();
    }
}
