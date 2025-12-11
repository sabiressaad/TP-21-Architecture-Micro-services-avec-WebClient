package com.example.servicecar.entities;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Entité Vehicle (Voiture) - représente un véhicule dans le système.
 * Maintient une référence à un Client via clientId (pattern microservices).
 * 
 * @author tp-microservices
 */
@Entity
@Table(name = "vehicles")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Car {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String marque;
    
    @Column(nullable = false, length = 100)
    private String modele;

    /**
     * Clé étrangère logique vers le service Client.
     * En architecture microservices, les relations inter-services se font via ID + appels HTTP.
     */
    @Column(name = "owner_id")
    private Long clientId;

    /**
     * Objet Client enrichi depuis le service distance (SERVICE-CLIENT).
     * Non persisté en base, reconstruit à la demande pour les réponses JSON.
     */
    @Transient
    private Client client;

    public Car() {}

    public Car(Long id, String marque, String modele, Long clientId) {
        this.id = id;
        this.marque = marque;
        this.modele = modele;
        this.clientId = clientId;
    }

    public Car(String marque, String modele, Long clientId) {
        this.marque = marque;
        this.modele = modele;
        this.clientId = clientId;
    }

    public Long getId() { 
        return id; 
    }

    public void setId(Long id) { 
        this.id = id; 
    }

    public String getMarque() { 
        return marque; 
    }

    public void setMarque(String marque) { 
        this.marque = marque; 
    }

    public String getModele() { 
        return modele; 
    }

    public void setModele(String modele) { 
        this.modele = modele; 
    }

    public Long getClientId() { 
        return clientId; 
    }

    public void setClientId(Long clientId) { 
        this.clientId = clientId; 
    }

    public Client getClient() { 
        return client; 
    }

    public void setClient(Client client) { 
        this.client = client; 
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", marque='" + marque + '\'' +
                ", modele='" + modele + '\'' +
                ", clientId=" + clientId +
                ", client=" + (client != null ? client.getId() : "null") +
                '}';
    }
}