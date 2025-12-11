package com.example.serviceclient.entities;

import jakarta.persistence.*;

/**
 * Entité Client - représente un utilisateur du système.
 * Persistée dans la base clientservicedb avec auto-incrément sur l'ID.
 */
@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nom;

    @Column(name = "age_years")
    private Float age;

    public Client() {
        // Constructeur vide requis par Hibernate pour l'instanciation par réflexion
    }

    public Client(Long id, String nom, Float age) {
        this.id = id;
        this.nom = nom;
        this.age = age;
    }

    public Client(String nom, Float age) {
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
        return "Client{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", age=" + age +
                '}';
    }
}