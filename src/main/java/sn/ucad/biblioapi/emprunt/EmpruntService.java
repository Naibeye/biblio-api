package sn.ucad.biblioapi.emprunt;

import java.time.Clock;
import java.time.LocalDate;
import org.springframework.stereotype.Service;

@Service
public class EmpruntService {
    // TODO ch05 (TP 2 bis, étape 1) : recevoir aussi EmpruntProperties par le constructeur.
    private final Clock horloge;

    public EmpruntService(Clock horloge) {
        this.horloge = horloge;
    }

    // TODO ch05 : dateRetourPrevue(Categorie categorie), avec la durée lue dans la règle
    // de la catégorie au lieu de 14 jours écrits en dur.
    public LocalDate dateRetourPrevue() {
        return LocalDate.now(horloge).plusDays(14);
    }
}
