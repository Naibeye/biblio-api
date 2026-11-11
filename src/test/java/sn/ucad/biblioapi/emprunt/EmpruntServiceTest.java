package sn.ucad.biblioapi.emprunt;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneOffset;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sn.ucad.biblioapi.adherent.Adherent;
import sn.ucad.biblioapi.adherent.AdherentRepository;
import sn.ucad.biblioapi.adherent.Categorie;
import sn.ucad.biblioapi.livre.Livre;
import sn.ucad.biblioapi.livre.LivreRepository;

@ExtendWith(MockitoExtension.class)
class EmpruntServiceTest {

    @Mock EmpruntRepository emprunts;          // faux dépôts créés par Mockito
    @Mock LivreRepository livres;
    @Mock AdherentRepository adherents;

    // Le 1er mars 2026 à midi, pour toujours : les dates deviennent prévisibles.
    Clock horloge = Clock.fixed(Instant.parse("2026-03-01T12:00:00Z"), ZoneOffset.UTC);
    EmpruntProperties regles = new EmpruntProperties(
            new EmpruntProperties.Regle(14, 3, 500),          // étudiant
            new EmpruntProperties.Regle(28, 5, 0));           // enseignant

    EmpruntService service;
    Livre livre = new Livre("978-2-1234", "Clean Code", 2008);
    Adherent amina = new Adherent("Amina", "amina@ucad.sn", Categorie.ETUDIANT);

    @BeforeEach
    void preparer() {
        // Injection par constructeur : on assemble le service à la main, sans Spring.
        service = new EmpruntService(emprunts, livres, adherents, regles, horloge);
    }

    // TODO ch10 (TP 7, étape 1) : les tests, sur le modèle « Étant donné / Quand / Alors » :
    //   emprunter_fixe_la_date_de_retour_a_14_jours_pour_un_etudiant   (2026-03-15)
    //   refuse_un_livre_deja_emprunte                                 (ConflitException, rien enregistré)
    //   rendre_avec_trois_jours_de_retard_coute_1500                   (prévu le 26/02, rendu le 01/03)
    //   refuse_au_dela_de_la_limite
    //   un_enseignant_garde_le_livre_28_jours                          (2026-03-29)
    //   un_enseignant_en_retard_ne_paie_rien
}
