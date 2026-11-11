package sn.ucad.biblioapi.adherent;

public record AdherentReponse(Long id, String nom, String email, Categorie categorie) {
    public static AdherentReponse depuis(Adherent a) {
        return new AdherentReponse(a.getId(), a.getNom(), a.getEmail(), a.getCategorie());
    }
}
