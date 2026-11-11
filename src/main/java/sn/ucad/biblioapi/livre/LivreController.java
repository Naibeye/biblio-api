package sn.ucad.biblioapi.livre;

import jakarta.validation.Valid;
import java.net.URI;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import sn.ucad.biblioapi.commun.PageReponse;

@RestController
@RequestMapping("/api/livres")
public class LivreController {
    private final LivreService service;

    public LivreController(LivreService service) {
        this.service = service;
    }

    @GetMapping
    public PageReponse<LivreReponse> lister(@RequestParam(required = false) String titre,
                                            @RequestParam(required = false) String auteur,
                                            @RequestParam(required = false) Integer annee,
                                            Pageable pageable) {
        return service.rechercher(titre, auteur, annee, pageable);
    }

    @GetMapping("/{id}")
    public LivreReponse lire(@PathVariable Long id) {
        return service.parId(id);
    }

    @PostMapping
    public ResponseEntity<LivreReponse> creer(@Valid @RequestBody LivreRequete requete) {
        LivreReponse cree = service.creer(requete.isbn(), requete.titre(), requete.annee());
        URI adresse = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(cree.id()).toUri();
        return ResponseEntity.created(adresse).body(cree);
    }

    @PutMapping("/{id}")
    public LivreReponse modifier(@PathVariable Long id, @Valid @RequestBody LivreRequete requete) {
        return service.modifier(id, requete.titre(), requete.annee());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimer(@PathVariable Long id) {
        service.supprimer(id);
        return ResponseEntity.noContent().build();
    }
}
