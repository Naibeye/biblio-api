package sn.ucad.biblioapi.emprunt;

import java.time.LocalDate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sn.ucad.biblioapi.adherent.Categorie;

@RestController
public class EmpruntController {
    private final EmpruntService service;

    public EmpruntController(EmpruntService service) {
        this.service = service;
    }

    @GetMapping("/api/emprunts/date-retour")       // ?categorie=ENSEIGNANT
    public LocalDate dateRetour(@RequestParam(defaultValue = "ETUDIANT") Categorie categorie) {
        return service.dateRetourPrevue(categorie);
    }
}
