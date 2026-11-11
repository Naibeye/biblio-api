package sn.ucad.biblioapi.auteur;

public record AuteurReponse(Long id, String nom, String nationalite) {
    public static AuteurReponse depuis(Auteur a) {
        return new AuteurReponse(a.id(), a.nom(), a.nationalite());
    }
}
