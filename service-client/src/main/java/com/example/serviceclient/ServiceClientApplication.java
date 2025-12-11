package com.example.serviceclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Service principal de gestion des clients.
 * Expose les endpoints REST pour CRUD sur les entités Client.
 * S'enregistre automatiquement auprès d'Eureka Server.
 */
@SpringBootApplication
public class ServiceClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceClientApplication.class, args);
    }

}
