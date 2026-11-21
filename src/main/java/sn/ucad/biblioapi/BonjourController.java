package sn.ucad.biblioapi;
import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BonjourController {

    private static final Logger log = LoggerFactory.getLogger(BonjourController.class);

    @GetMapping("/bonjour")
    public String bonjour() {
        log.debug("Appel de GET /bonjour");
        return "Bonjour, bienvenue sur BiblioAPI !";
    }

    @GetMapping("/bonjour/perso")
    public String perso(@RequestParam(defaultValue = "inconnu") String nom) {
        log.debug("Appel de GET /bonjour/perso avec nom={}", nom);
        return "Bonjour " + nom + " !";
    }

    @GetMapping("/bonjour/{nom}")
    public String bonjourNom(@PathVariable String nom) {
        log.debug("Appel de GET /bonjour/{}", nom);
        return "Bonjour " + nom + " !";
    }
    @GetMapping("/info")
public Info info() {
    log.debug("Appel de GET /info");
    return new Info("BiblioAPI", "0.0.1", LocalDateTime.now());
}
}