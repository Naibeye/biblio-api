package sn.ucad.biblioapi.livre;

// Ce que le client ENVOIE pour créer ou modifier un livre : pas d'id.
// TODO ch07 (TP 4, étape 2) : contraintes de validation, avec un message clair pour chacune.
public record LivreRequete(String isbn, String titre, Integer annee) { }
