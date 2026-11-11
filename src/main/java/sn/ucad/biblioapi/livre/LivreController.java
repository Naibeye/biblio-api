package sn.ucad.biblioapi.livre;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LivreController {
    private static final Logger log = LoggerFactory.getLogger(LivreController.class);
    private final LivreService service;

    public LivreController(LivreService service) {
        this.service = service;
        log.info("4. Construction de LivreController");
    }

    @GetMapping("/api/livres")
    public List<Livre> lister() {
        return service.tous();
    }

    @GetMapping("/api/livres/instance")       // le même numéro à chaque appel : un seul objet
    public String instance() {
        return "LivreService n°" + Integer.toHexString(System.identityHashCode(service));
    }
}
