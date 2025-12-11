package com.example.servicecar.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Configuration du WebClient pour les appels inter-services.
 * 
 * @LoadBalanced active la résolution de noms Eureka.
 * Permet d'utiliser http://SERVICE-CLIENT/... au lieu de http://ip:port/...
 * 
 * Sans cette annotation, Spring ne peut pas résoudre "SERVICE-CLIENT" -> erreur.
 */
@Configuration
public class WebClientConfig {

    /**
     * Fournit un WebClient.Builder singleton avec support LoadBalancer.
     * Chaque appel HTTP via ce builder utilise la découverte de service Eureka.
     */
    @Bean
    @LoadBalanced
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }
}