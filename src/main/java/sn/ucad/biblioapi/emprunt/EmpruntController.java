package sn.ucad.biblioapi.emprunt;

import java.time.LocalDate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmpruntController {
    private final EmpruntService service;

    public EmpruntController(EmpruntService service) {
        this.service = service;
    }

    // TODO ch05 (TP 2 bis, étape 1) : paramètre ?categorie=ENSEIGNANT (ETUDIANT par défaut)
    @GetMapping("/api/emprunts/date-retour")
    public LocalDate dateRetour() {
        return service.dateRetourPrevue();
    }
}
