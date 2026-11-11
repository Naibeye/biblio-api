package sn.ucad.biblioapi.emprunt;

import java.time.LocalDate;

public record EmpruntReponse(Long id, Long livreId, String titre, String adherent,
                             LocalDate dateEmprunt, LocalDate dateRetourPrevue, LocalDate dateRetour) {
    public static EmpruntReponse depuis(Emprunt e) {
        return new EmpruntReponse(e.getId(), e.getLivre().getId(), e.getLivre().getTitre(),
                e.getAdherent().getEmail(), e.getDateEmprunt(), e.getDateRetourPrevue(), e.getDateRetour());
    }
}
