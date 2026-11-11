package sn.ucad.biblioapi.livre;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record LivreRequete(
        @NotBlank(message = "L'ISBN est obligatoire")
        @Pattern(regexp = "[0-9-]{10,17}", message = "ISBN : chiffres et tirets, 10 à 17 caractères")
        String isbn,

        @NotBlank(message = "Le titre est obligatoire")
        @Size(max = 200, message = "Le titre ne doit pas dépasser 200 caractères")
        String titre,

        @NotNull(message = "L'année est obligatoire")
        @Min(value = 1450, message = "Aucun livre imprimé avant 1450")
        Integer annee
) { }
