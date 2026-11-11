package sn.ucad.biblioapi.livre;

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

// TODO ch07 (TP 4, étape 1) : ne manipuler que des DTO — LivreRequete en entrée (avec @Valid),
// LivreReponse en sortie. Les erreurs (404, 409) viennent des exceptions du service : les
// ResponseEntity.notFound() disparaissent. PUT ne modifie que le titre et l'année.
@RestController
@RequestMapping("/api/livres")        // préfixe commun à toutes les méthodes de la classe
public class LivreController {
    private final LivreService service;

    public LivreController(LivreService service) {
        this.service = service;
    }

    @GetMapping
    public List<Livre> lister(@RequestParam(required = false) String titre) {
        if (titre == null) {
            return service.tous();
        }
        return service.rechercher(titre);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livre> lire(@PathVariable Long id) {
        return service.parId(id)
                .map(ResponseEntity::ok)                      // trouvé : 200 + le livre
                .orElse(ResponseEntity.notFound().build());  // absent : 404, corps vide
    }

    @PostMapping
    public ResponseEntity<Livre> creer(@RequestBody Livre livre) {
        Livre cree = service.creer(livre);
        URI adresse = ServletUriComponentsBuilder.fromCurrentRequest()  // /api/livres
                .path("/{id}")                                          // /api/livres/{id}
                .buildAndExpand(cree.id())                              // /api/livres/3
                .toUri();
        return ResponseEntity.created(adresse).body(cree);              // 201 + Location
    }

    @PutMapping("/{id}")
    public ResponseEntity<Livre> remplacer(@PathVariable Long id, @RequestBody Livre livre) {
        return service.remplacer(id, livre)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimer(@PathVariable Long id) {
        if (service.supprimer(id)) {
            return ResponseEntity.noContent().build();   // 204 : fait, rien à renvoyer
        }
        return ResponseEntity.notFound().build();        // 404
    }
}
