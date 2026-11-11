package sn.ucad.biblioapi.auteur;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AuteurRequete(@NotBlank @Size(max = 100) String nom, @Size(max = 100) String nationalite) { }
