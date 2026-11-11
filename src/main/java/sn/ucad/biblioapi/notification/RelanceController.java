package sn.ucad.biblioapi.notification;

// TODO ch13 : code de démonstration des chapitres 3 à 5, à retirer du projet livré.
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RelanceController {
    private final RelanceService service;

    public RelanceController(RelanceService service) {
        this.service = service;
    }

    @GetMapping("/api/relances/essai")
    public String essai(@RequestParam String destinataire) {
        return "Relance envoyée par " + service.relancer(destinataire);
    }
}
