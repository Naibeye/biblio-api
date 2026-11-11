package sn.ucad.biblioapi.livre;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

// TODO ch07 (TP 4, étape 3) : lever RessourceIntrouvableException (livre inconnu) et
// ConflitException (ISBN déjà utilisé) au lieu de renvoyer Optional ou boolean :
//   Livre parId(Long id), Livre creer(String isbn, String titre, int annee),
//   Livre modifier(Long id, String titre, int annee), void supprimer(Long id)
@Service
public class LivreService {
    private final LivreRepository depot;

    public LivreService(LivreRepository depot) { this.depot = depot; }

    public List<Livre> tous() { return depot.findAll(); }

    public List<Livre> rechercher(String motDansTitre) {
        return depot.findAll().stream()
                .filter(l -> l.titre().toLowerCase().contains(motDansTitre.toLowerCase()))
                .toList();
    }

    public Optional<Livre> parId(Long id) { return depot.findById(id); }

    public Livre creer(Livre livre) {
        return depot.save(livre.avecId(null));      // l'id éventuellement envoyé est ignoré
    }

    public Optional<Livre> remplacer(Long id, Livre livre) {
        if (!depot.existsById(id)) return Optional.empty();
        return Optional.of(depot.save(livre.avecId(id)));
    }

    public boolean supprimer(Long id) {
        if (!depot.existsById(id)) return false;
        depot.deleteById(id);
        return true;
    }
}
