package org.example.minikattkompis.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.web.client.RestClient;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CatRestControllerTest {

    @LocalServerPort
    private int port;

    @Test
    void shouldReturnOkStatus() {
        String url = "http://localhost:" + port + "/api/cats/all";
        RestClient client = RestClient.create();

        ResponseEntity<String> response = client.get()
                .uri(url)
                .header("Version", "2")
                .retrieve()
                .toEntity(String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void shouldAddCatSuccessfully() {
        String url = "http://localhost:" + port + "/api/cats/add";
        RestClient client = RestClient.create();

        String json = """
            {
              "name": "Whiskers",
              "age": 3,
              "favoriteToy": "Boll"
            }
            """;

        ResponseEntity<String> response = client.post()
                .uri(url)
                .header("Version", "2")
                .contentType(MediaType.APPLICATION_JSON)
                .body(json)
                .retrieve()
                .toEntity(String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).contains("Whiskers", "Boll", "3");
    }
}
