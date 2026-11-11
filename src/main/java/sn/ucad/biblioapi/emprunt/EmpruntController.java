package sn.ucad.biblioapi.emprunt;

import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

// TODO ch11 (TP 8a, étape 4) : recevoir Authentication — emprunter pour utilisateur.getName()
// (l'email connecté) ; rendre en précisant si la personne est bibliothécaire ;
// GET /api/moi/emprunts -> les emprunts de la personne connectée.
@RestController
public class EmpruntController {
    private final EmpruntService service;

    public EmpruntController(EmpruntService service) {
        this.service = service;
    }

    @PostMapping("/api/emprunts")
    public ResponseEntity<EmpruntReponse> emprunter(@Valid @RequestBody EmpruntRequete requete) {
        EmpruntReponse e = service.emprunter(requete.livreId(), requete.adherentId());
        URI adresse = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(e.id()).toUri();
        return ResponseEntity.created(adresse).body(e);
    }

    @PostMapping("/api/emprunts/{id}/retour")
    public RetourReponse rendre(@PathVariable Long id) {
        return service.rendre(id);
    }

    @GetMapping("/api/emprunts/retards")
    public List<EmpruntReponse> retards() {
        return service.retards();
    }
}
