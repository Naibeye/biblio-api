package sn.ucad.biblioapi.auteur;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Repository;

// TODO ch09 : remplacer par une interface JpaRepository<Auteur, Long> avec findByNationaliteIgnoreCase.
@Repository
public class AuteurRepository {
    private final Map<Long, Auteur> auteurs = new ConcurrentHashMap<>();
    private final AtomicLong compteur = new AtomicLong();

    public List<Auteur> findAll()             { return List.copyOf(auteurs.values()); }
    public Optional<Auteur> findById(Long id) { return Optional.ofNullable(auteurs.get(id)); }
    public boolean existsById(Long id)        { return auteurs.containsKey(id); }
    public void deleteById(Long id)           { auteurs.remove(id); }

    public Auteur save(Auteur auteur) {
        Auteur aRanger = auteur.id() == null ? auteur.avecId(compteur.incrementAndGet()) : auteur;
        auteurs.put(aRanger.id(), aRanger);
        return aRanger;
    }
}
