package sn.ucad.biblioapi.livre;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import sn.ucad.biblioapi.auteur.Auteur;

@Entity
public class Livre {
    public static final int ANNEE_RECENT = 2020;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 20)
    private String isbn;

    @Column(nullable = false, length = 200)
    private String titre;

    private Integer annee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "auteur_id")
    private Auteur auteur;

    protected Livre() { }

    public Livre(String isbn, String titre, Integer annee) {
        this.isbn = isbn;
        this.titre = titre;
        this.annee = annee;
    }

    public Long getId() { return id; }
    public String getIsbn() { return isbn; }
    public String getTitre() { return titre; }
    public Integer getAnnee() { return annee; }
    public Auteur getAuteur() { return auteur; }

    public boolean estRecent() {
        return annee != null && annee >= ANNEE_RECENT;
    }

    public void modifier(String titre, Integer annee) {
        this.titre = titre;
        this.annee = annee;
    }

    public void attribuerA(Auteur auteur) {
        this.auteur = auteur;
    }
}
