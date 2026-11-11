package sn.ucad.biblioapi.emprunt;

import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class EmpruntController {
    private final EmpruntService service;

    public EmpruntController(EmpruntService service) {
        this.service = service;
    }

    @PostMapping("/api/emprunts")
    public ResponseEntity<EmpruntReponse> emprunter(@Valid @RequestBody EmpruntRequete requete,
                                                    Authentication utilisateur) {
        EmpruntReponse e = service.emprunter(requete.livreId(), utilisateur.getName());
        URI adresse = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(e.id()).toUri();
        return ResponseEntity.created(adresse).body(e);
    }

    @PostMapping("/api/emprunts/{id}/retour")
    public RetourReponse rendre(@PathVariable Long id, Authentication utilisateur) {
        boolean bibliothecaire = utilisateur.getAuthorities().stream()
                .anyMatch(a -> "ROLE_BIBLIOTHECAIRE".equals(a.getAuthority()));
        return service.rendre(id, utilisateur.getName(), bibliothecaire);
    }

    @GetMapping("/api/moi/emprunts")
    public List<EmpruntReponse> mesEmprunts(Authentication utilisateur) {
        return service.deLAdherent(utilisateur.getName());
    }

    @GetMapping("/api/emprunts/retards")
    public List<EmpruntReponse> retards() {
        return service.retards();
    }
}
