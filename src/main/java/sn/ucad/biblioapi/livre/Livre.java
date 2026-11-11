package sn.ucad.biblioapi.livre;

// TODO ch08 (TP 5, étape 1) : transformer ce record en entité JPA (une classe) : table « livre »,
// id généré par la base, isbn obligatoire et unique (20 caractères), titre obligatoire (200),
// constructeur protégé sans argument, getters, méthode modifier(titre, annee).
public record Livre(Long id, String isbn, String titre, int annee) {
    // Renvoie une copie avec un autre identifiant (un record est immuable).
    public Livre avecId(Long nouvelId) {
        return new Livre(nouvelId, isbn, titre, annee);
    }
}
