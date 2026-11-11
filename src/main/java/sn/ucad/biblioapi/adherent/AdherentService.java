package sn.ucad.biblioapi.adherent;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.ucad.biblioapi.commun.ConflitException;

@Service
@Transactional(readOnly = true)
public class AdherentService {
    private final AdherentRepository depot;

    public AdherentService(AdherentRepository depot) {
        this.depot = depot;
    }

    public List<AdherentReponse> tous() {
        return depot.findAll().stream().map(AdherentReponse::depuis).toList();
    }

    @Transactional
    public AdherentReponse inscrire(String nom, String email, Categorie categorie) {
        if (depot.findByEmail(email).isPresent()) {
            throw new ConflitException("L'email " + email + " est déjà inscrit");
        }
        return AdherentReponse.depuis(depot.save(new Adherent(nom, email, categorie)));
    }
}
