package sn.ucad.biblioapi.livre;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Repository;

// TODO ch08 (TP 5, étapes 1 et 4) : remplacer cette classe par une interface qui étend
// JpaRepository<Livre, Long>, avec existsByIsbn et findByTitreContainingIgnoreCase.
@Repository
public class LivreRepository {
    private final Map<Long, Livre> livres = new ConcurrentHashMap<>();
    private final AtomicLong compteur = new AtomicLong();       // génère 1, 2, 3... sans doublon

    public List<Livre> findAll()             { return List.copyOf(livres.values()); }
    public Optional<Livre> findById(Long id) { return Optional.ofNullable(livres.get(id)); }
    public boolean existsById(Long id)       { return livres.containsKey(id); }
    public void deleteById(Long id)          { livres.remove(id); }

    public Livre save(Livre livre) {         // un livre sans id reçoit le suivant
        Livre aRanger = livre.id() == null ? livre.avecId(compteur.incrementAndGet()) : livre;
        livres.put(aRanger.id(), aRanger);
        return aRanger;
    }
}
