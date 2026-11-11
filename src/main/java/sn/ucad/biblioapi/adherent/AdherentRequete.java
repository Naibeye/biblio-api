package sn.ucad.biblioapi.adherent;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AdherentRequete(
        @NotBlank @Size(min = 2, max = 100) String nom,
        @NotBlank @Email String email,
        @NotNull Categorie categorie) { }
