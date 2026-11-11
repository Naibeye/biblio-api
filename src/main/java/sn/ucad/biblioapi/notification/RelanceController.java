package sn.ucad.biblioapi.notification;

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
