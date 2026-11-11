package sn.ucad.biblioapi.livre;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.ucad.biblioapi.commun.ConflitException;
import sn.ucad.biblioapi.commun.PageReponse;
import sn.ucad.biblioapi.commun.RessourceIntrouvableException;

@Service
@Transactional(readOnly = true)
public class LivreService {
    private final LivreRepository depot;

    public LivreService(LivreRepository depot) {
        this.depot = depot;
    }

    public PageReponse<LivreReponse> rechercher(String titre, String auteur, Integer annee, Pageable pageable) {
        Page<LivreReponse> page = depot.rechercher(titre, auteur, annee, pageable).map(LivreReponse::depuis);
        return new PageReponse<>(page.getContent(), page.getNumber(), page.getSize(),
                page.getTotalElements(), page.getTotalPages());
    }

    public LivreReponse parId(Long id) {
        return LivreReponse.depuis(trouver(id));
    }

    @Transactional
    public LivreReponse creer(String isbn, String titre, Integer annee) {
        if (depot.existsByIsbn(isbn)) {
            throw new ConflitException("L'ISBN " + isbn + " est déjà utilisé");
        }
        return LivreReponse.depuis(depot.save(new Livre(isbn, titre, annee)));
    }

    @Transactional
    public LivreReponse modifier(Long id, String titre, Integer annee) {
        Livre livre = trouver(id);
        livre.modifier(titre, annee);
        return LivreReponse.depuis(livre);
    }

    @Transactional
    public void supprimer(Long id) {
        depot.delete(trouver(id));
    }

    private Livre trouver(Long id) {
        return depot.findById(id)
                .orElseThrow(() -> new RessourceIntrouvableException("Livre " + id + " introuvable"));
    }
}
