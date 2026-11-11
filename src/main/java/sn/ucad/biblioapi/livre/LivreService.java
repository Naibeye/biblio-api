package sn.ucad.biblioapi.livre;

import java.util.List;
import org.springframework.stereotype.Service;
import sn.ucad.biblioapi.commun.ConflitException;
import sn.ucad.biblioapi.commun.RessourceIntrouvableException;

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

    public Livre parId(Long id) {
        return depot.findById(id)
                .orElseThrow(() -> new RessourceIntrouvableException("Livre " + id + " introuvable"));
    }

    public Livre creer(String isbn, String titre, int annee) {
        boolean dejaPris = depot.findAll().stream().anyMatch(l -> l.isbn().equals(isbn));
        if (dejaPris) {
            throw new ConflitException("L'ISBN " + isbn + " est déjà utilisé");
        }
        return depot.save(new Livre(null, isbn, titre, annee));
    }

    public Livre modifier(Long id, String titre, int annee) {      // l'ISBN ne change pas
        Livre livre = parId(id);
        return depot.save(new Livre(id, livre.isbn(), titre, annee));
    }

    public void supprimer(Long id) {
        if (!depot.existsById(id)) {
            throw new RessourceIntrouvableException("Livre " + id + " introuvable");
        }
        depot.deleteById(id);
    }
}
