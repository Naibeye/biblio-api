package sn.ucad.biblioapi.livre;

// TODO ch04 (TP 2, étape 1) : faire de cette classe un contrôleur REST.
public class LivreController {
    private final LivreService service;

    public LivreController(LivreService service) {
        this.service = service;
        // TODO ch04 : journaliser « 4. Construction de LivreController ».
    }

    // TODO ch04 (étape 2) : GET /api/livres          -> la liste renvoyée par le service
    // TODO ch04 (étape 4) : GET /api/livres/instance -> "LivreService n°" + l'identité du service
    //                       (Integer.toHexString(System.identityHashCode(service)))
}
