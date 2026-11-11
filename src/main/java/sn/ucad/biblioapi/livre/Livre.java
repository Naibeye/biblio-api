package sn.ucad.biblioapi.livre;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

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

    // TODO ch09 (TP 6, étape 1) : plusieurs livres → un auteur (chargement paresseux, colonne
    // auteur_id, peut rester null) ; getAuteur() et attribuerA(Auteur auteur).

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

    public boolean estRecent() {
        return annee != null && annee >= ANNEE_RECENT;
    }

    public void modifier(String titre, Integer annee) {
        this.titre = titre;
        this.annee = annee;
    }
}
