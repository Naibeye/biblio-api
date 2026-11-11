package sn.ucad.biblioapi.livre;

import java.util.List;

// TODO ch04 (TP 2, étape 1) : déclarer cette classe comme bean de la couche « service ».
public class LivreService {
    private final LivreRepository depot;

    public LivreService(LivreRepository depot) {
        this.depot = depot;
        // TODO ch04 : journaliser « 3. Construction de LivreService (dépôt reçu : ...) ».
    }

    public List<Livre> tous() {
        throw new UnsupportedOperationException("TODO ch04 : les livres, du plus ancien au plus récent");
    }

    public Livre ajouter(Livre livre) {
        throw new UnsupportedOperationException("TODO ch04 : refuser une année future, puis enregistrer");
    }
}
