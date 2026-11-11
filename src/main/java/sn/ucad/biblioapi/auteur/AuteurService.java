package sn.ucad.biblioapi.auteur;

import java.util.List;
import org.springframework.stereotype.Service;
import sn.ucad.biblioapi.commun.RessourceIntrouvableException;

// TODO ch09 : service transactionnel sur le dépôt JPA (renommer modifie l'entité suivie).
@Service
public class AuteurService {
    private final AuteurRepository depot;

    public AuteurService(AuteurRepository depot) {
        this.depot = depot;
    }

    public List<AuteurReponse> tous(String nationalite) {
        return depot.findAll().stream()
                .filter(a -> nationalite == null || nationalite.equalsIgnoreCase(a.nationalite()))
                .map(AuteurReponse::depuis)
                .toList();
    }

    public AuteurReponse parId(Long id) {
        return AuteurReponse.depuis(trouver(id));
    }

    public AuteurReponse creer(String nom, String nationalite) {
        return AuteurReponse.depuis(depot.save(new Auteur(null, nom, nationalite)));
    }

    public AuteurReponse renommer(Long id, String nom) {
        Auteur a = trouver(id);
        return AuteurReponse.depuis(depot.save(new Auteur(id, nom, a.nationalite())));
    }

    public void supprimer(Long id) {
        depot.deleteById(trouver(id).id());
    }

    private Auteur trouver(Long id) {
        return depot.findById(id)
                .orElseThrow(() -> new RessourceIntrouvableException("Auteur " + id + " introuvable"));
    }
}
