package sn.ucad.biblioapi.emprunt;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmpruntRepository extends JpaRepository<Emprunt, Long> {

    boolean existsByLivreIdAndDateRetourIsNull(Long livreId);

    long countByAdherentIdAndDateRetourIsNull(Long adherentId);

    @Query("select e from Emprunt e join fetch e.livre join fetch e.adherent where e.adherent.email = :email")
    List<Emprunt> findDeLAdherent(String email);

    @Query("""
            select e from Emprunt e join fetch e.livre join fetch e.adherent
            where e.dateRetour is null and e.dateRetourPrevue < :date
            """)
    List<Emprunt> retardsAu(LocalDate date);
}
