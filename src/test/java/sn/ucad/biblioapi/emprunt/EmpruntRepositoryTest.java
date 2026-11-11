package sn.ucad.biblioapi.emprunt;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import sn.ucad.biblioapi.adherent.Adherent;
import sn.ucad.biblioapi.adherent.AdherentRepository;
import sn.ucad.biblioapi.adherent.Categorie;
import sn.ucad.biblioapi.livre.Livre;
import sn.ucad.biblioapi.livre.LivreRepository;

@DataJpaTest
@ActiveProfiles("test")
class EmpruntRepositoryTest {

    @Autowired EmpruntRepository emprunts;
    @Autowired LivreRepository livres;
    @Autowired AdherentRepository adherents;

    @Test
    void un_livre_rendu_n_est_plus_sorti() {
        Livre livre = livres.save(new Livre("978-2-0001", "Clean Code", 2008));
        Adherent a = adherents.save(new Adherent("Amina", "amina@ucad.sn", Categorie.ETUDIANT));
        Emprunt e = emprunts.save(new Emprunt(livre, a, LocalDate.of(2026, 3, 1), 14));

        assertThat(emprunts.existsByLivreIdAndDateRetourIsNull(livre.getId())).isTrue();

        e.rendre(LocalDate.of(2026, 3, 10));
        emprunts.flush();

        assertThat(emprunts.existsByLivreIdAndDateRetourIsNull(livre.getId())).isFalse();
    }
}
