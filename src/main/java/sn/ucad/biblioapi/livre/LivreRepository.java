package sn.ucad.biblioapi.livre;

import java.util.List;
import java.util.Optional;

// TODO ch04 (TP 2, étape 1) : déclarer cette classe comme bean de la couche « dépôt ».
public class LivreRepository {

    // TODO ch04 : un Logger, et une Map<String, Livre> (clé : l'ISBN) sûre entre plusieurs fils.

    public LivreRepository() {
        // TODO ch04 : journaliser « 1. Construction de LivreRepository ».
    }

    // TODO ch04 : une méthode @PostConstruct qui range trois livres d'exemple, puis journalise
    //             « 2. @PostConstruct : 3 livres chargés ».
    // TODO ch04 (étape 8) : une méthode @PreDestroy qui journalise l'arrêt du dépôt.

    public List<Livre> findAll() {
        throw new UnsupportedOperationException("TODO ch04 : tous les livres");
    }

    public Optional<Livre> findByIsbn(String isbn) {
        throw new UnsupportedOperationException("TODO ch04 : le livre de cet ISBN");
    }

    public Livre save(Livre livre) {
        throw new UnsupportedOperationException("TODO ch04 : ranger le livre (clé : son ISBN)");
    }
}
