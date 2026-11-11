package sn.ucad.biblioapi.livre;

public record LivreReponse(Long id, String isbn, String titre, Integer annee, boolean recent) {
    public static LivreReponse depuis(Livre livre) {
        return new LivreReponse(livre.getId(), livre.getIsbn(), livre.getTitre(),
                livre.getAnnee(), livre.estRecent());
    }
}
