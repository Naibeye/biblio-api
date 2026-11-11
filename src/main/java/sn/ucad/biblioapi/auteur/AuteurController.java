package sn.ucad.biblioapi.auteur;

import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    public List<Auteur> lister(@RequestParam(required = false) String nationalite) {
        return nationalite == null ? service.tous() : service.deNationalite(nationalite);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Auteur> lire(@PathVariable Long id) {
        return service.parId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Auteur> creer(@RequestBody Auteur auteur) {
        Auteur cree = service.creer(auteur);
        URI adresse = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(cree.id()).toUri();
        return ResponseEntity.created(adresse).body(cree);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Auteur> remplacer(@PathVariable Long id, @RequestBody Auteur auteur) {
        return service.remplacer(id, auteur)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}")             // ne change que le nom : {"nom": "..."}
    public ResponseEntity<Auteur> renommer(@PathVariable Long id, @RequestBody ChangementNom c) {
        return service.parId(id)
                .map(a -> service.remplacer(id, new Auteur(id, c.nom(), a.nationalite())).orElseThrow())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimer(@PathVariable Long id) {
        if (service.supprimer(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
