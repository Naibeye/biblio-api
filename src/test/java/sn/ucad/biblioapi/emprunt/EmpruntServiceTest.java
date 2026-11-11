package sn.ucad.biblioapi.emprunt;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sn.ucad.biblioapi.adherent.Adherent;
import sn.ucad.biblioapi.adherent.AdherentRepository;
import sn.ucad.biblioapi.adherent.Categorie;
import sn.ucad.biblioapi.commun.ConflitException;
import sn.ucad.biblioapi.livre.Livre;
import sn.ucad.biblioapi.livre.LivreRepository;

@ExtendWith(MockitoExtension.class)
class EmpruntServiceTest {

    @Mock EmpruntRepository emprunts;
    @Mock LivreRepository livres;
    @Mock AdherentRepository adherents;

    // Le 1er mars 2026 à midi, pour toujours : les dates deviennent prévisibles.
    Clock horloge = Clock.fixed(Instant.parse("2026-03-01T12:00:00Z"), ZoneOffset.UTC);
    EmpruntProperties regles = new EmpruntProperties(
            new EmpruntProperties.Regle(14, 3, 500),
            new EmpruntProperties.Regle(28, 5, 0));

    EmpruntService service;
    Livre livre = new Livre("978-2-1234", "Clean Code", 2008);
    Adherent amina = new Adherent("Amina", "amina@ucad.sn", Categorie.ETUDIANT);

    @BeforeEach
    void preparer() {
        service = new EmpruntService(emprunts, livres, adherents, regles, horloge);
    }

    @Test
    void emprunter_fixe_la_date_de_retour_a_14_jours_pour_un_etudiant() {
        when(livres.findByIdPourMiseAJour(1L)).thenReturn(Optional.of(livre));
        when(adherents.findById(7L)).thenReturn(Optional.of(amina));
        when(emprunts.save(any(Emprunt.class))).thenAnswer(appel -> appel.getArgument(0));

        EmpruntReponse e = service.emprunter(1L, 7L);

        assertThat(e.dateRetourPrevue()).isEqualTo(LocalDate.of(2026, 3, 15));
    }

    @Test
    void refuse_un_livre_deja_emprunte() {
        when(livres.findByIdPourMiseAJour(1L)).thenReturn(Optional.of(livre));
        when(adherents.findById(7L)).thenReturn(Optional.of(amina));
        when(emprunts.existsByLivreIdAndDateRetourIsNull(1L)).thenReturn(true);

        assertThatThrownBy(() -> service.emprunter(1L, 7L))
                .isInstanceOf(ConflitException.class)
                .hasMessageContaining("déjà emprunté");
        verify(emprunts, never()).save(any());
    }

    @Test
    void rendre_avec_trois_jours_de_retard_coute_1500() {
        Emprunt e = new Emprunt(livre, amina, LocalDate.of(2026, 2, 12), 14);   // retour prévu le 26/02
        when(emprunts.findById(5L)).thenReturn(Optional.of(e));

        RetourReponse r = service.rendre(5L);

        assertThat(r.joursDeRetard()).isEqualTo(3);
        assertThat(r.penalite()).isEqualTo(1500.0);
    }

    @Test
    void refuse_au_dela_de_la_limite() {
        when(livres.findByIdPourMiseAJour(1L)).thenReturn(Optional.of(livre));
        when(adherents.findById(7L)).thenReturn(Optional.of(amina));
        when(emprunts.countByAdherentIdAndDateRetourIsNull(any())).thenReturn(3L);

        assertThatThrownBy(() -> service.emprunter(1L, 7L))
                .isInstanceOf(ConflitException.class)
                .hasMessageContaining("Limite de 3 emprunts");
    }

    @Test
    void un_enseignant_garde_le_livre_28_jours() {
        Adherent hassan = new Adherent("Hassan", "hassan@ucad.sn", Categorie.ENSEIGNANT);
        when(livres.findByIdPourMiseAJour(1L)).thenReturn(Optional.of(livre));
        when(adherents.findById(8L)).thenReturn(Optional.of(hassan));
        when(emprunts.save(any(Emprunt.class))).thenAnswer(appel -> appel.getArgument(0));

        assertThat(service.emprunter(1L, 8L).dateRetourPrevue()).isEqualTo(LocalDate.of(2026, 3, 29));
    }

    @Test
    void un_enseignant_en_retard_ne_paie_rien() {
        Adherent hassan = new Adherent("Hassan", "hassan@ucad.sn", Categorie.ENSEIGNANT);
        Emprunt e = new Emprunt(livre, hassan, LocalDate.of(2026, 1, 20), 28);   // prévu le 17/02
        when(emprunts.findById(5L)).thenReturn(Optional.of(e));

        RetourReponse r = service.rendre(5L);

        assertThat(r.joursDeRetard()).isEqualTo(12);
        assertThat(r.penalite()).isZero();
    }
}
