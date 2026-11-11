package sn.ucad.biblioapi.auteur;

import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/auteurs")
public class AuteurController {
    private final AuteurService service;

    public AuteurController(AuteurService service) {
        this.service = service;
    }

    @GetMapping
    public List<AuteurReponse> lister(@RequestParam(required = false) String nationalite) {
        return service.tous(nationalite);
    }

    @GetMapping("/{id}")
    public AuteurReponse lire(@PathVariable Long id) {
        return service.parId(id);
    }

    @PostMapping
    public ResponseEntity<AuteurReponse> creer(@Valid @RequestBody AuteurRequete requete) {
        AuteurReponse cree = service.creer(requete.nom(), requete.nationalite());
        URI adresse = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(cree.id()).toUri();
        return ResponseEntity.created(adresse).body(cree);
    }

    @PatchMapping("/{id}")
    public AuteurReponse renommer(@PathVariable Long id, @Valid @RequestBody ChangementNom changement) {
        return service.renommer(id, changement.nom());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimer(@PathVariable Long id) {
        service.supprimer(id);
        return ResponseEntity.noContent().build();
    }
}
