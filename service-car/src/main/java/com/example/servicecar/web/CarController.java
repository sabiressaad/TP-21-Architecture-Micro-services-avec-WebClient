package com.example.servicecar.web;

import com.example.servicecar.services.ClientApi;
import com.example.servicecar.repositories.CarRepository;
import com.example.servicecar.entities.Car;
import com.example.servicecar.entities.Client;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Contrôleur REST pour les opérations sur les voitures.
 * 
 * Pattern d'enrichissement : récupère les Cars en DB locale, puis appelle
 * SERVICE-CLIENT via WebClient pour enrichir chaque Car avec son Client.
 */
@RestController
@RequestMapping("/api/cars")
public class CarController {

    private final CarRepository repository;
    private final ClientApi clientApiService;

    public CarController(CarRepository repository, ClientApi clientApiService) {
        this.repository = repository;
        this.clientApiService = clientApiService;
    }

    /**
     * Crée une nouvelle voiture.
     * Sauvegarde uniquement dans carservicedb (DB locale).
     */
    @PostMapping
    public ResponseEntity<Car> createCar(@RequestBody Car carData) {
        Car savedCar = repository.save(carData);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCar);
    }

    /**
     * Récupère toutes les voitures avec enrichissement Client.
     * Pattern : SELECT DB locale + appels HTTP pour enrichir.
     */
    @GetMapping
    public ResponseEntity<List<Car>> retrieveAllCars() {
        List<Car> vehicles = repository.findAll();
        
        // Enrichissement via appels HTTP au service Client
        vehicles = vehicles.stream()
                .peek(car -> {
                    if (car.getClientId() != null) {
                        Client clientInfo = clientApiService.findClientById(car.getClientId());
                        car.setClient(clientInfo);
                    }
                })
                .collect(Collectors.toList());
        
        return ResponseEntity.ok(vehicles);
    }

    /**
     * Récupère un véhicule spécifique par ID avec enrichissement.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Car> retrieveCarById(@PathVariable Long id) {
        return repository.findById(id)
                .map(car -> {
                    if (car.getClientId() != null) {
                        car.setClient(clientApiService.findClientById(car.getClientId()));
                    }
                    return ResponseEntity.ok(car);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Filtre les voitures par propriétaire (clientId).
     * Optimisation : un seul appel HTTP client pour toutes les voitures du même propriétaire.
     */
    @GetMapping("/byClient/{clientId}")
    public ResponseEntity<List<Car>> retrieveCarsByOwner(@PathVariable Long clientId) {
        List<Car> vehiclesOfClient = repository.findByClientId(clientId);
        
        // Optimisation : récupérer le client une fois, l'assigner à toutes les voitures
        Client ownerInfo = clientApiService.findClientById(clientId);
        
        vehiclesOfClient.forEach(car -> car.setClient(ownerInfo));
        
        return ResponseEntity.ok(vehiclesOfClient);
    }

    /**
     * Met à jour une voiture existante.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable Long id, @RequestBody Car carData) {
        return repository.findById(id)
                .map(existing -> {
                    existing.setMarque(carData.getMarque());
                    existing.setModele(carData.getModele());
                    existing.setClientId(carData.getClientId());
                    return ResponseEntity.ok(repository.save(existing));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Supprime une voiture.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}