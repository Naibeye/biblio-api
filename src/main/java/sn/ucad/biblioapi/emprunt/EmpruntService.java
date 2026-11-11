package sn.ucad.biblioapi.emprunt;

import java.time.Clock;
import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.ucad.biblioapi.adherent.Adherent;
import sn.ucad.biblioapi.adherent.AdherentRepository;
import sn.ucad.biblioapi.commun.ConflitException;
import sn.ucad.biblioapi.commun.RessourceIntrouvableException;
import sn.ucad.biblioapi.livre.Livre;
import sn.ucad.biblioapi.livre.LivreRepository;

// TODO ch11 (TP 8a, étape 4) : emprunter(Long livreId, String emailAdherent) — adhérent trouvé par
// son email (« Aucun adhérent pour ce compte » sinon) ; rendre(Long empruntId, String utilisateur,
// boolean bibliothecaire) — 404 si l'emprunt est celui d'un autre (sauf bibliothécaire) ;
// deLAdherent(String email).
@Service
@Transactional(readOnly = true)
public class EmpruntService {
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

    @Transactional                                   // tout ou rien
    public EmpruntReponse emprunter(Long livreId, Long adherentId) {
        // Verrou sur la ligne du livre : deux emprunts simultanés du même livre passent l'un après l'autre.
        Livre livre = livres.findByIdPourMiseAJour(livreId)
                .orElseThrow(() -> new RessourceIntrouvableException("Livre " + livreId + " introuvable"));
        Adherent adherent = adherents.findById(adherentId)
                .orElseThrow(() -> new RessourceIntrouvableException("Adhérent " + adherentId + " introuvable"));
        EmpruntProperties.Regle regle = regles.pour(adherent.getCategorie());

        if (emprunts.existsByLivreIdAndDateRetourIsNull(livreId)) {
            throw new ConflitException("Ce livre est déjà emprunté");
        }
        long enCours = emprunts.countByAdherentIdAndDateRetourIsNull(adherentId);
        if (enCours >= regle.maxEmprunts()) {
            throw new ConflitException("Limite de " + regle.maxEmprunts() + " emprunts atteinte");
        }
        Emprunt e = emprunts.save(new Emprunt(livre, adherent, LocalDate.now(horloge), regle.dureeJours()));
        return EmpruntReponse.depuis(e);             // le DTO est construit DANS la transaction
    }

    @Transactional
    public RetourReponse rendre(Long empruntId) {
        Emprunt e = emprunts.findById(empruntId)
                .orElseThrow(() -> new RessourceIntrouvableException("Emprunt " + empruntId + " introuvable"));
        long retard = e.rendre(LocalDate.now(horloge));      // pas de save() : l'entité est suivie
        double penalite = retard * regles.pour(e.getAdherent().getCategorie()).penaliteParJour();
        return new RetourReponse(EmpruntReponse.depuis(e), retard, penalite);
    }

    public List<EmpruntReponse> retards() {
        return emprunts.retardsAu(LocalDate.now(horloge)).stream().map(EmpruntReponse::depuis).toList();
    }
}
