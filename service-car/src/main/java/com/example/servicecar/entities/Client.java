package com.example.servicecar.entities;

/**
 * DTO Client - classe de mappage pour les réponses du service distant SERVICE-CLIENT.
 * 
 * Cette classe n'est PAS une entité JPA, elle sert uniquement à désérialiser
 * le JSON reçu des appels HTTP vers SERVICE-CLIENT et à enrichir les réponses Car.
 * 
 * Elle est volontairement simple pour respecter le pattern DTO (Data Transfer Object).
 */
public class Client {
    
    private Long id;
    
    private String nom;
    
    private Float age;

    public Client() {
        // Constructeur vide pour la désérialisation JSON
    }

    public Client(Long id, String nom, Float age) {
        this.id = id;
        this.nom = nom;
        this.age = age;
    }

    public Long getId() { 
        return id; 
    }

    public void setId(Long id) { 
        this.id = id; 
    }

    public String getNom() { 
        return nom; 
    }

    public void setNom(String nom) { 
        this.nom = nom; 
    }

    public Float getAge() { 
        return age; 
    }

    public void setAge(Float age) { 
        this.age = age; 
    }

    @Override
    public String toString() {
        return "ClientDTO{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", age=" + age +
                '}';
    }
}