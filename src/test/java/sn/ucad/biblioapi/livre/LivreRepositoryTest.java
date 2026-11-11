package sn.ucad.biblioapi.livre;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
class LivreRepositoryTest {

    @Autowired LivreRepository livres;

    @Test
    void la_recherche_par_titre_ignore_la_casse() {
        livres.save(new Livre("978-2-0001", "Clean Code", 2008));
        livres.save(new Livre("978-2-0002", "Spring en action", 2022));

        assertThat(livres.findByTitreContainingIgnoreCase("SPRING"))
                .extracting(Livre::getTitre)
                .containsExactly("Spring en action");
    }
}
