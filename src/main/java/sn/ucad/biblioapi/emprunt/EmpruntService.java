package sn.ucad.biblioapi.emprunt;

import java.time.Clock;
import java.time.LocalDate;
import org.springframework.stereotype.Service;

@Service
public class EmpruntService {
    private final Clock horloge;

    public EmpruntService(Clock horloge) {
        this.horloge = horloge;
    }

    public LocalDate dateRetourPrevue() {
        return LocalDate.now(horloge).plusDays(14);
    }
}
