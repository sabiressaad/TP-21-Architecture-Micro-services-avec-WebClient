package com.example.servicecar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Service principal de gestion des voitures.
 * Coordonne les données locales (Car) avec le service distant (CLIENT).
 * Utilise WebClient avec LoadBalancer pour la communication inter-services.
 */
@SpringBootApplication
public class ServiceCarApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceCarApplication.class, args);
    }

}
