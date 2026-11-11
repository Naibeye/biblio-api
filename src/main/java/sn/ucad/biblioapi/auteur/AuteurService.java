package sn.ucad.biblioapi.auteur;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.ucad.biblioapi.commun.RessourceIntrouvableException;

@Service
@Transactional(readOnly = true)
public class AuteurService {
    private final AuteurRepository depot;

    public AuteurService(AuteurRepository depot) {
        this.depot = depot;
    }

    public List<AuteurReponse> tous(String nationalite) {
        List<Auteur> auteurs = nationalite == null ? depot.findAll()
                : depot.findByNationaliteIgnoreCase(nationalite);
        return auteurs.stream().map(AuteurReponse::depuis).toList();
    }

    public AuteurReponse parId(Long id) {
        return AuteurReponse.depuis(trouver(id));
    }

    @Transactional
    public AuteurReponse creer(String nom, String nationalite) {
        return AuteurReponse.depuis(depot.save(new Auteur(nom, nationalite)));
    }

    @Transactional
    public AuteurReponse renommer(Long id, String nom) {
        Auteur a = trouver(id);
        a.renommer(nom);
        return AuteurReponse.depuis(a);
    }

    @Transactional
    public void supprimer(Long id) {
        depot.delete(trouver(id));
    }

    private Auteur trouver(Long id) {
        return depot.findById(id)
                .orElseThrow(() -> new RessourceIntrouvableException("Auteur " + id + " introuvable"));
    }
}
