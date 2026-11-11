package sn.ucad.biblioapi.livre;

import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
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

@RestController
@RequestMapping("/api/livres")
public class LivreController {
    private final LivreService service;

    public LivreController(LivreService service) {
        this.service = service;
    }

    @GetMapping
    public List<LivreReponse> lister(@RequestParam(required = false) String titre) {
        List<Livre> livres = titre == null ? service.tous() : service.rechercher(titre);
        return livres.stream().map(LivreReponse::depuis).toList();
    }

    @GetMapping("/{id}")
    public LivreReponse lire(@PathVariable Long id) {
        return LivreReponse.depuis(service.parId(id));   // l'exception éventuelle « remonte »
    }

    @PostMapping
    public ResponseEntity<LivreReponse> creer(@Valid @RequestBody LivreRequete requete) {
        Livre cree = service.creer(requete.isbn(), requete.titre(), requete.annee());
        URI adresse = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(cree.id()).toUri();
        return ResponseEntity.created(adresse).body(LivreReponse.depuis(cree));
    }

    @PutMapping("/{id}")
    public LivreReponse modifier(@PathVariable Long id, @Valid @RequestBody LivreRequete requete) {
        return LivreReponse.depuis(service.modifier(id, requete.titre(), requete.annee()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimer(@PathVariable Long id) {
        service.supprimer(id);
        return ResponseEntity.noContent().build();
    }
}
