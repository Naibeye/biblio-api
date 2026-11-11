package sn.ucad.biblioapi.livre;

import jakarta.persistence.LockModeType;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LivreRepository extends JpaRepository<Livre, Long> {

    boolean existsByIsbn(String isbn);

    List<Livre> findByTitreContainingIgnoreCase(String morceau);

    @Query("select l from Livre l where l.annee between :debut and :fin order by l.titre")
    List<Livre> publiesEntre(@Param("debut") int debut, @Param("fin") int fin);

    @Query("select l from Livre l left join fetch l.auteur")
    List<Livre> findAllAvecAuteur();

    // Recherche multicritère : un critère null est ignoré.
    // Les cast(...) disent à PostgreSQL le type d'un paramètre null (sinon : « lower(bytea) »).
    @EntityGraph(attributePaths = "auteur")
    @Query("""
            select l from Livre l left join l.auteur a
            where (cast(:titre as String) is null
                   or lower(l.titre) like lower(concat('%', cast(:titre as String), '%')))
              and (cast(:auteur as String) is null
                   or lower(a.nom) like lower(concat('%', cast(:auteur as String), '%')))
              and (cast(:annee as Integer) is null or l.annee = :annee)
            """)
    Page<Livre> rechercher(@Param("titre") String titre, @Param("auteur") String auteur,
                           @Param("annee") Integer annee, Pageable pageable);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select l from Livre l where l.id = :id")
    Optional<Livre> findByIdPourMiseAJour(@Param("id") Long id);
}
