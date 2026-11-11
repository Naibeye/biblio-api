package sn.ucad.biblioapi.emprunt;

import java.time.Clock;
import java.time.LocalDate;
import org.springframework.stereotype.Service;
import sn.ucad.biblioapi.adherent.Categorie;

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
