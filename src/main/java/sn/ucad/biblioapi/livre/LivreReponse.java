package sn.ucad.biblioapi.livre;

// Ce que l'API RENVOIE : avec l'id, et une information calculée.
public record LivreReponse(Long id, String isbn, String titre, Integer annee, boolean recent) {

    public static LivreReponse depuis(Livre livre) {        // conversion modèle → DTO
        return new LivreReponse(livre.id(), livre.isbn(), livre.titre(), livre.annee(),
                                livre.annee() >= 2020);
    }
}
