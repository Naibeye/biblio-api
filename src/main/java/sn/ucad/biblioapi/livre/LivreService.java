package sn.ucad.biblioapi.livre;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class LivreService {
    private final LivreRepository depot;

    public LivreService(LivreRepository depot) { this.depot = depot; }

    public List<Livre> tous() {
        throw new UnsupportedOperationException("TODO ch06 : tous les livres");
    }

    public List<Livre> rechercher(String motDansTitre) {
        throw new UnsupportedOperationException("TODO ch06 : les livres dont le titre contient le mot (sans tenir compte de la casse)");
    }

    public Optional<Livre> parId(Long id) {
        throw new UnsupportedOperationException("TODO ch06 : le livre, s'il existe");
    }

    public Livre creer(Livre livre) {
        throw new UnsupportedOperationException("TODO ch06 : enregistrer le livre en ignorant l'id éventuellement envoyé");
    }

    public Optional<Livre> remplacer(Long id, Livre livre) {
        throw new UnsupportedOperationException("TODO ch06 : remplacer le livre s'il existe (vide sinon)");
    }

    public boolean supprimer(Long id) {
        throw new UnsupportedOperationException("TODO ch06 : supprimer ; renvoyer false si le livre n'existe pas");
    }
}
