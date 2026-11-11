package sn.ucad.biblioapi.auteur;

public record Auteur(Long id, String nom, String nationalite) {
    public Auteur avecId(Long nouvelId) {
        return new Auteur(nouvelId, nom, nationalite);
    }
}
