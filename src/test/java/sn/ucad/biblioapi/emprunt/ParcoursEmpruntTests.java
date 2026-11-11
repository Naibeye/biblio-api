package sn.ucad.biblioapi.emprunt;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.RequestPostProcessor;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ParcoursEmpruntTests {

    // Des utilisateurs simulés : pas besoin de comptes en base pour ces tests.
    static final RequestPostProcessor MOUSSA = user("moussa@ucad.sn").roles("BIBLIOTHECAIRE");
    static final RequestPostProcessor FATOU = user("fatou@ucad.sn").roles("ADHERENT");
    static final RequestPostProcessor AWA = user("awa@ucad.sn").roles("ADHERENT");

    @Autowired MockMvc mvc;

    @Test
    void emprunter_refuser_rendre_puis_reemprunter() throws Exception {
        long livreId = creer("/api/livres", MOUSSA, """
                {"isbn": "978-2-8888", "titre": "Les Bouts de bois de Dieu", "annee": 1960}
                """);
        creer("/api/adherents", MOUSSA, """
                {"nom": "Fatou", "email": "fatou@ucad.sn", "categorie": "ETUDIANT"}
                """);
        String emprunt = """
                {"livreId": %d}
                """.formatted(livreId);

        long empruntId = creer("/api/emprunts", FATOU, emprunt);   // l'adhérente est la personne connectée

        mvc.perform(post("/api/emprunts").with(FATOU).contentType(MediaType.APPLICATION_JSON).content(emprunt))
           .andExpect(status().isConflict());                       // le livre est déjà sorti

        mvc.perform(post("/api/emprunts/" + empruntId + "/retour").with(AWA))
           .andExpect(status().isNotFound());                       // pas son emprunt : 404

        mvc.perform(post("/api/emprunts/" + empruntId + "/retour").with(FATOU))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$.joursDeRetard").value(0));

        creer("/api/emprunts", FATOU, emprunt);                     // rendu : on peut le réemprunter
    }

    /** Envoie un POST, vérifie le 201 et renvoie l'identifiant de l'élément créé. */
    private long creer(String adresse, RequestPostProcessor utilisateur, String json) throws Exception {
        String corps = mvc.perform(post(adresse).with(utilisateur)
                        .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();
        return ((Number) JsonPath.read(corps, "$.id")).longValue();
    }
}
