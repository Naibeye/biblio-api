package sn.ucad.biblioapi.adherent;

// TODO ch08 : contraintes — nom obligatoire (2 à 100 caractères), email obligatoire et valide,
// catégorie obligatoire.
public record AdherentRequete(String nom, String email, Categorie categorie) { }
