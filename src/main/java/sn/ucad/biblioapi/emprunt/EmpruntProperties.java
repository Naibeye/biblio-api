package sn.ucad.biblioapi.emprunt;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;
import sn.ucad.biblioapi.adherent.Categorie;

@ConfigurationProperties(prefix = "biblio.emprunt")
@Validated
public record EmpruntProperties(@NotNull @Valid Regle etudiant, @NotNull @Valid Regle enseignant) {

    public record Regle(@Positive int dureeJours, @Positive int maxEmprunts,
                        @PositiveOrZero double penaliteParJour) { }

    public Regle pour(Categorie categorie) {
        return categorie == Categorie.ENSEIGNANT ? enseignant : etudiant;
    }
}
