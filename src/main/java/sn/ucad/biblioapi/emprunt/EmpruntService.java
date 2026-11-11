package sn.ucad.biblioapi.emprunt;

import java.time.Clock;
import java.time.LocalDate;

// TODO ch04 (TP 2, étape 5) : déclarer cette classe comme bean de la couche « service ».
public class EmpruntService {
    private final Clock horloge;

    public EmpruntService(Clock horloge) {
        this.horloge = horloge;
    }

    public LocalDate dateRetourPrevue() {
        throw new UnsupportedOperationException("TODO ch04 : aujourd'hui (selon l'horloge) + 14 jours");
    }
}
