package sn.ucad.biblioapi.emprunt;

// TODO ch13 : code de démonstration des chapitres 3 à 5, à retirer du projet livré.
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import sn.ucad.biblioapi.adherent.Categorie;

@RestController
public class RegleController {
    private final EmpruntProperties regles;

    public RegleController(EmpruntProperties regles) {
        this.regles = regles;
    }

    @GetMapping("/api/regles/{categorie}")
    public EmpruntProperties.Regle regle(@PathVariable Categorie categorie) {
        return regles.pour(categorie);
    }
}
