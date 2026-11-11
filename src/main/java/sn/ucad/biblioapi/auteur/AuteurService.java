package sn.ucad.biblioapi.auteur;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class AuteurService {
    private final AuteurRepository depot;

    public AuteurService(AuteurRepository depot) { this.depot = depot; }

    public List<Auteur> tous() { return depot.findAll(); }

    public List<Auteur> deNationalite(String nationalite) {
        return depot.findAll().stream()
                .filter(a -> nationalite.equalsIgnoreCase(a.nationalite()))
                .toList();
    }

    public Optional<Auteur> parId(Long id) { return depot.findById(id); }

    public Auteur creer(Auteur auteur) {
        return depot.save(auteur.avecId(null));
    }

    public Optional<Auteur> remplacer(Long id, Auteur auteur) {
        if (!depot.existsById(id)) return Optional.empty();
        return Optional.of(depot.save(auteur.avecId(id)));
    }

    public boolean supprimer(Long id) {
        if (!depot.existsById(id)) return false;
        depot.deleteById(id);
        return true;
    }
}
