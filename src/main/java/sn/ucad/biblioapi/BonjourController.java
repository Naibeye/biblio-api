package sn.ucad.biblioapi;

// TODO ch13 : code de démonstration des chapitres 3 à 5, à retirer du projet livré.
import java.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController                       // cette classe répond à des requêtes HTTP
public class BonjourController {
    private static final Logger log = LoggerFactory.getLogger(BonjourController.class);

    @GetMapping("/bonjour")           // GET /bonjour appelle cette méthode
    public String bonjour() {
        log.info("Appel de /bonjour");
        return "Bonjour, bienvenue sur BiblioAPI !";
    }

    @GetMapping("/bonjour/perso")     // GET /bonjour/perso?nom=Amina
    public String bonjourPerso(@RequestParam(defaultValue = "inconnu") String nom) {
        log.debug("Paramètre nom = {}", nom);      // {} est remplacé par la valeur
        return "Bonjour " + nom + " !";
    }

    @GetMapping("/bonjour/{nom}")     // GET /bonjour/Moussa
    public String bonjourChemin(@PathVariable String nom) {
        return "Bonjour " + nom + " !";
    }

    @GetMapping("/info")
    public Info info() {
        return new Info("BiblioAPI", "0.0.1", LocalDateTime.now());
    }
}
