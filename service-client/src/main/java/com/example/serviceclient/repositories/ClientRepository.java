package com.example.serviceclient.repositories;

import com.example.serviceclient.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository pour l'accès aux données Client.
 * Fournit automatiquement des opérations CRUD via JpaRepository.
 */
@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    // Les méthodes findAll(), findById(), save(), deleteById() sont héritées
    // Extensible pour ajouter des méthodes dérivées si nécessaire (ex: findByNom)
}
