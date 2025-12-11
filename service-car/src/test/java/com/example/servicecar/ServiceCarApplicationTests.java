package com.example.servicecar;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Tests de base pour le service Car.
 * Valide le chargement du contexte Spring avec toutes les dépendances
 * (WebClient, Eureka Client, JPA, etc.)
 */
@SpringBootTest
class ServiceCarApplicationTests {

    /**
     * Test de smoke - vérifie que le contexte Spring se charge correctement.
     * Confirme la configuration WebClient, LoadBalancer, et connexion JPA.
     */
    @Test
    void contextLoads() {
        // Test passant si le contexte Spring démarre sans erreur
    }

}
