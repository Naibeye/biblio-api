package sn.ucad.biblioapi.livre;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class LivreRepository {
    private static final Logger log = LoggerFactory.getLogger(LivreRepository.class);
    private final Map<String, Livre> livres = new ConcurrentHashMap<>();   // clé : l'ISBN

    public LivreRepository() {
        log.info("1. Construction de LivreRepository");
    }

    @PostConstruct                       // appelé une fois, juste après la construction
    void chargerExemples() {
        save(new Livre("978-2-07-036024-5", "L'Aventure ambiguë", 1961));
        save(new Livre("978-2-84261-001-0", "Une si longue lettre", 1979));
        save(new Livre("978-0-13-235088-4", "Clean Code", 2008));
        log.info("2. @PostConstruct : {} livres chargés", livres.size());
    }

    @PreDestroy                          // appelé à l'arrêt de l'application
    void fermer() {
        log.info("Arrêt : LivreRepository libère ses ressources");
    }

    public List<Livre> findAll()                   { return List.copyOf(livres.values()); }
    public Optional<Livre> findByIsbn(String isbn) { return Optional.ofNullable(livres.get(isbn)); }
    public Livre save(Livre livre)                 { livres.put(livre.isbn(), livre); return livre; }
}
