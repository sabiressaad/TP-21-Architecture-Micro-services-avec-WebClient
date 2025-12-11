package com.example.serviceclient.web;

import com.example.serviceclient.entities.Client;
import com.example.serviceclient.repositories.ClientRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Contrôleur REST pour les opérations sur les clients.
 * Expose les endpoints : GET, POST pour gérer les entités Client.
 */
@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientRepository repository;

    public ClientController(ClientRepository repository) {
        this.repository = repository;
    }

    /**
     * Récupère la liste complète des clients.
     */
    @GetMapping
    public ResponseEntity<List<Client>> retrieveAllClients() {
        List<Client> clients = repository.findAll();
        return ResponseEntity.ok(clients);
    }

    /**
     * Récupère un client spécifique par son ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Client> retrieveClientById(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Crée un nouveau client.
     */
    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody Client clientData) {
        Client savedClient = repository.save(clientData);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedClient);
    }

    /**
     * Met à jour un client existant.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Long id, @RequestBody Client clientData) {
        return repository.findById(id)
                .map(existing -> {
                    existing.setNom(clientData.getNom());
                    existing.setAge(clientData.getAge());
                    return ResponseEntity.ok(repository.save(existing));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Supprime un client.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}