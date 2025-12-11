package com.example.serviceclient;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Tests de base pour le service Client.
 * Valide le chargement du contexte Spring.
 */
@SpringBootTest
class ServiceClientApplicationTests {

    /**
     * Test de smoke - vérifie que le contexte Spring se charge correctement.
     * Confirme que toutes les dépendances et beans sont bien configurés.
     */
    @Test
    void contextLoads() {
        // Test passant si le contexte Spring démarre sans erreur
    }

}
