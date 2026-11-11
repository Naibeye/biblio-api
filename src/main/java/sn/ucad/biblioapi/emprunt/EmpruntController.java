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

    @GetMapping("/api/emprunts/date-retour")
    public LocalDate dateRetour() {
        return service.dateRetourPrevue();
    }
}
