package sn.ucad.biblioapi.auteur;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuteurRepository extends JpaRepository<Auteur, Long> {
    List<Auteur> findByNationaliteIgnoreCase(String nationalite);
}
