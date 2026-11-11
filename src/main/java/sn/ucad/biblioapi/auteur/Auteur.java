package sn.ucad.biblioapi.auteur;

// TODO ch09 (TP 6, étape 1) : transformer ce record en entité JPA : id généré, nom obligatoire
// (100 caractères), nationalité ; getters et méthode renommer(nom).
public record Auteur(Long id, String nom, String nationalite) {
    public Auteur avecId(Long nouvelId) {
        return new Auteur(nouvelId, nom, nationalite);
    }
}
