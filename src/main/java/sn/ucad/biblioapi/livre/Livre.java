package sn.ucad.biblioapi.livre;

public record Livre(Long id, String isbn, String titre, int annee) {
    // Renvoie une copie avec un autre identifiant (un record est immuable).
    public Livre avecId(Long nouvelId) {
        return new Livre(nouvelId, isbn, titre, annee);
    }
}
