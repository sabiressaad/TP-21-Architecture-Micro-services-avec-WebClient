package com.example.servicecar.repositories;

import com.example.servicecar.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository pour l'accès aux données Car.
 * Spring Data génère automatiquement les requêtes SQL à partir des signatures de méthodes.
 */
@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    
    /**
     * Recherche tous les véhicules appartenant à un client spécifique.
     * Méthode dérivée : SQL générée automatiquement -> SELECT * FROM vehicles WHERE owner_id = ?
     */
    List<Car> findByClientId(Long clientId);
}