package sn.ucad.biblioapi.emprunt;

import jakarta.validation.constraints.NotNull;

public record EmpruntRequete(@NotNull(message = "Le livre est obligatoire") Long livreId,
                             @NotNull(message = "L'adhérent est obligatoire") Long adherentId) { }
