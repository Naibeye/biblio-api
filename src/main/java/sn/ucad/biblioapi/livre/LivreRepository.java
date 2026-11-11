package sn.ucad.biblioapi.livre;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LivreRepository extends JpaRepository<Livre, Long> {

    boolean existsByIsbn(String isbn);

    List<Livre> findByTitreContainingIgnoreCase(String morceau);

    @Query("select l from Livre l where l.annee between :debut and :fin order by l.titre")
    List<Livre> publiesEntre(@Param("debut") int debut, @Param("fin") int fin);
}
