package com.example.eurekaserver;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Tests de base pour Eureka Server.
 * Valide que le serveur de découverte démarre correctement.
 */
@SpringBootTest
class EurekaserverApplicationTests {

    /**
     * Test de smoke - vérifie que le contexte Eureka Server se charge correctement.
     * Confirme que @EnableEurekaServer est bien activé.
     */
    @Test
    void contextLoads() {
        // Test passant si le contexte Eureka démarre sans erreur
    }

}
