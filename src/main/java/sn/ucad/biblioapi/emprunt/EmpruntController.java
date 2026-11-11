package sn.ucad.biblioapi.emprunt;

// TODO ch04 (TP 2, étape 5) : contrôleur REST.
public class EmpruntController {
    private final EmpruntService service;

    public EmpruntController(EmpruntService service) {
        this.service = service;
    }

    // TODO ch04 : GET /api/emprunts/date-retour -> service.dateRetourPrevue()
}
