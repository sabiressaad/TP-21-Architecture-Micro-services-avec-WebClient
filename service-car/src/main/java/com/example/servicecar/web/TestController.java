package com.example.servicecar.web;

import com.example.servicecar.services.ClientApi;
import com.example.servicecar.entities.Client;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Contrôleur de test pour valider la communication inter-services.
 * 
 * Ce endpoint permet de vérifier rapidement que :
 * 1. WebClient est correctement configuré avec @LoadBalanced
 * 2. SERVICE-CLIENT est enregistré dans Eureka
 * 3. La désérialisation JSON fonctionne
 * 
 * Utilisation : GET /api/test/client/{id}
 */
@RestController
@RequestMapping("/api/test")
public class TestController {
    
    private final ClientApi clientApiService;

    public TestController(ClientApi clientApiService) {
        this.clientApiService = clientApiService;
    }

    /**
     * Test de connectivité avec le service CLIENT distant.
     * 
     * @param id L'ID du client à récupérer
     * @return Le client récupéré depuis SERVICE-CLIENT ou 404
     */
    @GetMapping("/client/{id}")
    public ResponseEntity<Client> testRemoteClientFetch(@PathVariable Long id) {
        Client remoteClient = clientApiService.findClientById(id);
        
        if (remoteClient != null) {
            return ResponseEntity.ok(remoteClient);
        }
        
        return ResponseEntity.notFound().build();
    }

    /**
     * Health check simple.
     */
    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Service CAR opérationnel et prêt à communiquer avec SERVICE-CLIENT");
    }
}