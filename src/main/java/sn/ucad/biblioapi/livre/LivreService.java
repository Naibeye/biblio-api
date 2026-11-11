package sn.ucad.biblioapi.livre;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.ucad.biblioapi.commun.ConflitException;
import sn.ucad.biblioapi.commun.RessourceIntrouvableException;

// TODO ch09 (étape 5) : rechercher(titre, auteur, annee, pageable) -> PageReponse<LivreReponse>.
// Avec open-in-view=false, renvoyer des LivreReponse construits DANS la transaction
// (parId, creer, modifier), comme EmpruntService.
@Service
@Transactional(readOnly = true)
public class LivreService {
    private final LivreRepository depot;

    public LivreService(LivreRepository depot) {
        this.depot = depot;
    }

    public List<Livre> tous() {
        return depot.findAll();
    }

    public List<Livre> rechercher(String motDansTitre) {
        return depot.findByTitreContainingIgnoreCase(motDansTitre);
    }

    public Livre parId(Long id) {
        return depot.findById(id)
                .orElseThrow(() -> new RessourceIntrouvableException("Livre " + id + " introuvable"));
    }

    @Transactional
    public Livre creer(String isbn, String titre, Integer annee) {
        if (depot.existsByIsbn(isbn)) {
            throw new ConflitException("L'ISBN " + isbn + " est déjà utilisé");
        }
        return depot.save(new Livre(isbn, titre, annee));
    }

    @Transactional
    public Livre modifier(Long id, String titre, Integer annee) {
        Livre livre = parId(id);
        livre.modifier(titre, annee);           // entité suivie : mise à jour à la fin de la transaction
        return livre;
    }

    @Transactional
    public void supprimer(Long id) {
        depot.delete(parId(id));
    }
}
