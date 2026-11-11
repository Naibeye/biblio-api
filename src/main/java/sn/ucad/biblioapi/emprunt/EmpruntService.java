package sn.ucad.biblioapi.emprunt;

import java.time.Clock;
import java.time.LocalDate;
import org.springframework.stereotype.Service;
import sn.ucad.biblioapi.adherent.Categorie;

// TODO ch09 (TP 6, étape 2) : remplacer dateRetourPrevue par les vraies opérations, transactionnelles :
//   EmpruntReponse emprunter(Long livreId, Long adherentId) — livre (verrouillé) et adhérent existants,
//     livre pas déjà sorti, limite d'emprunts de la catégorie, date de retour selon la règle ;
//   RetourReponse rendre(Long empruntId) — jours de retard et pénalité de la catégorie ;
//   List<EmpruntReponse> retards() (exercice 9.2).
// Dépendances : EmpruntRepository, LivreRepository, AdherentRepository, EmpruntProperties, Clock.
@Service
public class EmpruntService {
    private final EmpruntProperties regles;
    private final Clock horloge;

    public EmpruntService(EmpruntProperties regles, Clock horloge) {
        this.regles = regles;
        this.horloge = horloge;
    }

    public LocalDate dateRetourPrevue(Categorie categorie) {
        return LocalDate.now(horloge).plusDays(regles.pour(categorie).dureeJours());
    }
}
