package org.example.minikattkompis.utility;

import java.util.UUID;

public class RandomUsernameGenerator {
    public static String getRandomUsername() {
        return "CatEnjoyer_" +  UUID.randomUUID().toString().substring(0, 8);
    }
}
