package sn.ucad.biblioapi.auteur;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Auteur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nom;

    private String nationalite;

    protected Auteur() { }

    public Auteur(String nom, String nationalite) {
        this.nom = nom;
        this.nationalite = nationalite;
    }

    public Long getId() { return id; }
    public String getNom() { return nom; }
    public String getNationalite() { return nationalite; }

    public void renommer(String nom) {
        this.nom = nom;
    }
}
