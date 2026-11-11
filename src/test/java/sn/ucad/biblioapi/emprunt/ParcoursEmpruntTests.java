package sn.ucad.biblioapi.emprunt;

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

// TODO ch11 : chaque requête avec un utilisateur simulé — .with(user("moussa@ucad.sn").roles("BIBLIOTHECAIRE"))
// pour le livre et l'adhérent, l'adhérente elle-même pour emprunter et rendre ; vérifier qu'un autre
// adhérent reçoit 404 en voulant rendre son emprunt.
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ParcoursEmpruntTests {

    @Autowired MockMvc mvc;

    @Test
    void emprunter_refuser_rendre_puis_reemprunter() throws Exception {
        long livreId = creer("/api/livres", """
                {"isbn": "978-2-8888", "titre": "Les Bouts de bois de Dieu", "annee": 1960}
                """);
        long adherentId = creer("/api/adherents", """
                {"nom": "Fatou", "email": "fatou@ucad.sn", "categorie": "ETUDIANT"}
                """);
        String emprunt = """
                {"livreId": %d, "adherentId": %d}
                """.formatted(livreId, adherentId);

        long empruntId = creer("/api/emprunts", emprunt);

        mvc.perform(post("/api/emprunts").contentType(MediaType.APPLICATION_JSON).content(emprunt))
           .andExpect(status().isConflict());                       // le livre est déjà sorti

        mvc.perform(post("/api/emprunts/" + empruntId + "/retour"))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$.joursDeRetard").value(0));

        creer("/api/emprunts", emprunt);                            // rendu : on peut le réemprunter
    }

    /** Envoie un POST, vérifie le 201 et renvoie l'identifiant de l'élément créé. */
    private long creer(String adresse, String json) throws Exception {
        String corps = mvc.perform(post(adresse).contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();
        return ((Number) JsonPath.read(corps, "$.id")).longValue();
    }
}
