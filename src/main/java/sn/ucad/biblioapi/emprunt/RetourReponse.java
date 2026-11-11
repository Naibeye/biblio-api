package sn.ucad.biblioapi.emprunt;

public record RetourReponse(EmpruntReponse emprunt, long joursDeRetard, double penalite) { }
