package sn.ucad.biblioapi.emprunt;

import jakarta.validation.constraints.NotNull;

// TODO ch11 (TP 8a, étape 4) : retirer adherentId — l'adhérent est la personne connectée.
public record EmpruntRequete(@NotNull(message = "Le livre est obligatoire") Long livreId,
                             @NotNull(message = "L'adhérent est obligatoire") Long adherentId) { }
