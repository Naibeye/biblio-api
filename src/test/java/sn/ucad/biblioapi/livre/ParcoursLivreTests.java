package sn.ucad.biblioapi.livre;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ParcoursLivreTests {

    @Autowired MockMvc mvc;

    @Test
    void creer_puis_relire_un_livre() throws Exception {
        String adresse = mvc.perform(post("/api/livres")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"isbn": "978-2-4242", "titre": "Le Petit Prince", "annee": 1943}
                                """))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andReturn().getResponse().getHeader("Location");

        mvc.perform(get(adresse))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$.titre").value("Le Petit Prince"));
    }
}
