package com.example.servicecar.services;

import com.example.servicecar.entities.Client;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Service de communication avec le service CLIENT distant.
 * 
 * Responsabilité : appeler SERVICE-CLIENT pour enrichir les réponses Car.
 * Utilise WebClient avec LoadBalancer pour la résolution dynamique d'Eureka.
 */
@Service
public class ClientApi {
    
    private static final Logger logger = LoggerFactory.getLogger(ClientApi.class);
    private static final String SERVICE_BASE_URL = "http://SERVICE-CLIENT/api/clients";
    
    private final WebClient.Builder webClientBuilder;

    public ClientApi(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    /**
     * Récupère un Client distant par son ID.
     * 
     * @param clientId L'ID du client à récupérer
     * @return Le client reçu du service CLIENT, ou null en cas d'erreur
     */
    public Client findClientById(Long clientId) {
        try {
            return webClientBuilder.build()
                    .get()
                    .uri(SERVICE_BASE_URL + "/" + clientId)
                    .retrieve()
                    .bodyToMono(Client.class)
                    .block();
        } catch (Exception e) {
            logger.error("Erreur lors de la récupération du client ID {}: {}", clientId, e.getMessage());
            return null;
        }
    }
}