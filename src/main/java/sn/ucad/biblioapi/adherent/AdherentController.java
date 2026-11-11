package sn.ucad.biblioapi.adherent;

import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/adherents")
public class AdherentController {
    private final AdherentService service;

    public AdherentController(AdherentService service) {
        this.service = service;
    }

    @GetMapping
    public List<AdherentReponse> lister() {
        return service.tous();
    }

    @PostMapping
    public ResponseEntity<AdherentReponse> inscrire(@Valid @RequestBody AdherentRequete r) {
        AdherentReponse cree = service.inscrire(r.nom(), r.email(), r.categorie());
        URI adresse = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(cree.id()).toUri();
        return ResponseEntity.created(adresse).body(cree);
    }
}
