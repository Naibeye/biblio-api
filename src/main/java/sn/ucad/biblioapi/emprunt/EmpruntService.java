package sn.ucad.biblioapi.emprunt;

import java.time.Clock;
import java.time.LocalDate;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.ucad.biblioapi.adherent.Adherent;
import sn.ucad.biblioapi.adherent.AdherentRepository;
import sn.ucad.biblioapi.commun.ConflitException;
import sn.ucad.biblioapi.commun.RessourceIntrouvableException;
import sn.ucad.biblioapi.livre.Livre;
import sn.ucad.biblioapi.livre.LivreRepository;

@Service
@Transactional(readOnly = true)
public class EmpruntService {
    private static final Logger log = LoggerFactory.getLogger(EmpruntService.class);

    private final EmpruntRepository emprunts;
    private final LivreRepository livres;
    private final AdherentRepository adherents;
    private final EmpruntProperties regles;
    private final Clock horloge;

    public EmpruntService(EmpruntRepository emprunts, LivreRepository livres,
                          AdherentRepository adherents, EmpruntProperties regles, Clock horloge) {
        this.emprunts = emprunts;
        this.livres = livres;
        this.adherents = adherents;
        this.regles = regles;
        this.horloge = horloge;
    }

    @Transactional
    public EmpruntReponse emprunter(Long livreId, String emailAdherent) {
        // Verrou sur la ligne du livre : deux emprunts simultanés du même livre passent l'un après l'autre.
        Livre livre = livres.findByIdPourMiseAJour(livreId)
                .orElseThrow(() -> new RessourceIntrouvableException("Livre " + livreId + " introuvable"));
        Adherent adherent = adherents.findByEmail(emailAdherent)
                .orElseThrow(() -> new RessourceIntrouvableException("Aucun adhérent pour ce compte"));
        EmpruntProperties.Regle regle = regles.pour(adherent.getCategorie());

        if (emprunts.existsByLivreIdAndDateRetourIsNull(livreId)) {
            throw new ConflitException("Ce livre est déjà emprunté");
        }
        long enCours = emprunts.countByAdherentIdAndDateRetourIsNull(adherent.getId());
        if (enCours >= regle.maxEmprunts()) {
            throw new ConflitException("Limite de " + regle.maxEmprunts() + " emprunts atteinte");
        }
        Emprunt e = emprunts.save(new Emprunt(livre, adherent, LocalDate.now(horloge), regle.dureeJours()));
        log.info("Emprunt {} créé, retour prévu le {}", e.getId(), e.getDateRetourPrevue());
        return EmpruntReponse.depuis(e);
    }

    @Transactional
    public RetourReponse rendre(Long empruntId, String utilisateur, boolean bibliothecaire) {
        Emprunt e = emprunts.findById(empruntId)
                .orElseThrow(() -> new RessourceIntrouvableException("Emprunt " + empruntId + " introuvable"));
        if (!bibliothecaire && !e.getAdherent().getEmail().equals(utilisateur)) {
            // 404 plutôt que 403 : ne pas révéler l'existence de l'emprunt d'un autre.
            throw new RessourceIntrouvableException("Emprunt " + empruntId + " introuvable");
        }
        long retard = e.rendre(LocalDate.now(horloge));
        double penalite = retard * regles.pour(e.getAdherent().getCategorie()).penaliteParJour();
        log.info("Emprunt {} rendu avec {} jour(s) de retard", e.getId(), retard);
        return new RetourReponse(EmpruntReponse.depuis(e), retard, penalite);
    }

    public List<EmpruntReponse> deLAdherent(String email) {
        return emprunts.findDeLAdherent(email).stream().map(EmpruntReponse::depuis).toList();
    }

    public List<EmpruntReponse> retards() {
        return emprunts.retardsAu(LocalDate.now(horloge)).stream().map(EmpruntReponse::depuis).toList();
    }
}
