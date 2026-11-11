package sn.ucad.biblioapi.livre;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LivreService {
    private static final Logger log = LoggerFactory.getLogger(LivreService.class);
    private final LivreRepository depot;

    public LivreService(LivreRepository depot) {   // Spring appelle ce constructeur
        this.depot = depot;                        // et lui passe le bean LivreRepository
        log.info("3. Construction de LivreService (dépôt reçu : {})", depot.getClass().getSimpleName());
    }

    public List<Livre> tous() {
        return depot.findAll().stream()
                .sorted(Comparator.comparing(Livre::annee))   // du plus ancien au plus récent
                .toList();
    }

    public Livre ajouter(Livre livre) {
        if (livre.annee() > LocalDate.now().getYear()) {
            throw new IllegalArgumentException("Année de publication dans le futur");
        }
        return depot.save(livre);
    }
}
