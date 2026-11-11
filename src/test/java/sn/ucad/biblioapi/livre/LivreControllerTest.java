package sn.ucad.biblioapi.livre;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import sn.ucad.biblioapi.commun.ConflitException;
import sn.ucad.biblioapi.commun.RessourceIntrouvableException;
import sn.ucad.biblioapi.securite.JwtConfig;
import sn.ucad.biblioapi.securite.SecuriteConfig;

@WebMvcTest(LivreController.class)
@Import({SecuriteConfig.class, JwtConfig.class})
@ActiveProfiles("test")
class LivreControllerTest {

    @Autowired MockMvc mvc;
    @MockitoBean LivreService service;

    @Test
    void lire_un_livre_existant_renvoie_200_et_le_json() throws Exception {
        when(service.parId(1L)).thenReturn(new LivreReponse(1L, "978-2-1234", "Clean Code", 2008, false));

        mvc.perform(get("/api/livres/1"))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$.titre").value("Clean Code"))
           .andExpect(jsonPath("$.recent").value(false));
    }

    @Test
    void lire_un_livre_inconnu_renvoie_404() throws Exception {
        when(service.parId(99L)).thenThrow(new RessourceIntrouvableException("Livre 99 introuvable"));

        mvc.perform(get("/api/livres/99"))
           .andExpect(status().isNotFound())
           .andExpect(jsonPath("$.detail").value("Livre 99 introuvable"));
    }

    @Test
    @WithMockUser(roles = "BIBLIOTHECAIRE")
    void creer_un_livre_sans_titre_renvoie_400() throws Exception {
        mvc.perform(post("/api/livres")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {"isbn": "978-2-9999", "titre": "", "annee": 2024}
                        """))
           .andExpect(status().isBadRequest())
           .andExpect(jsonPath("$.erreurs.titre").value("Le titre est obligatoire"));
        verifyNoInteractions(service);
    }

    @Test
    void un_anonyme_ne_peut_pas_supprimer() throws Exception {
        mvc.perform(delete("/api/livres/1")).andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(roles = "ADHERENT")
    void un_adherent_ne_peut_pas_supprimer() throws Exception {
        mvc.perform(delete("/api/livres/1")).andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "BIBLIOTHECAIRE")
    void un_bibliothecaire_peut_supprimer() throws Exception {
        mvc.perform(delete("/api/livres/1")).andExpect(status().isNoContent());
    }

    @Test
    @WithMockUser(roles = "BIBLIOTHECAIRE")
    void creer_renvoie_201_et_l_adresse() throws Exception {
        when(service.creer("978-2-7777", "Une si longue lettre", 1979))
                .thenReturn(new LivreReponse(3L, "978-2-7777", "Une si longue lettre", 1979, false));

        mvc.perform(post("/api/livres").contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {"isbn": "978-2-7777", "titre": "Une si longue lettre", "annee": 1979}
                        """))
           .andExpect(status().isCreated())
           .andExpect(header().string("Location", "http://localhost/api/livres/3"));
    }

    @Test
    @WithMockUser(roles = "BIBLIOTHECAIRE")
    void un_isbn_deja_pris_renvoie_409() throws Exception {
        when(service.creer(any(), any(), any())).thenThrow(new ConflitException("L'ISBN est déjà utilisé"));

        mvc.perform(post("/api/livres").contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {"isbn": "978-2-1234", "titre": "Clean Code", "annee": 2008}
                        """))
           .andExpect(status().isConflict());
    }
}
