package sn.ucad.biblioapi.livre;

// Ce que l'API RENVOIE : avec l'id, et une information calculée.
public record LivreReponse(Long id, String isbn, String titre, Integer annee, boolean recent) {

    public static LivreReponse depuis(Livre livre) {
        throw new UnsupportedOperationException("TODO ch07 : convertir le livre (recent : année >= 2020)");
    }
}
