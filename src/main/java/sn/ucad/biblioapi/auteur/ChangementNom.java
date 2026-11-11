package sn.ucad.biblioapi.auteur;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ChangementNom(@NotBlank @Size(max = 100) String nom) { }
