package org.example.minikattkompis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MiniKattKompisApplication {
    public static void main(String[] args) {
        SpringApplication.run(MiniKattKompisApplication.class, args);
    }
}
