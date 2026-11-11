package sn.ucad.biblioapi.livre;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/livres")        // préfixe commun à toutes les méthodes de la classe
public class LivreController {
    private final LivreService service;

    public LivreController(LivreService service) {
        this.service = service;
    }

    // TODO ch06 : GET    /api/livres?titre=...  -> 200 et la liste (filtre facultatif)
    // TODO ch06 : GET    /api/livres/{id}       -> 200 et le livre, ou 404 (ResponseEntity)
    // TODO ch06 : POST   /api/livres            -> 201, en-tête Location et le livre créé
    // TODO ch06 : PUT    /api/livres/{id}       -> 200 et le livre remplacé, ou 404
    // TODO ch06 : DELETE /api/livres/{id}       -> 204, ou 404
}
