package sn.ucad.biblioapi.emprunt;

// TODO ch09 (TP 6, étape 2) : entité JPA — id généré ; livre et adhérent obligatoires (@ManyToOne,
// LAZY) ; dateEmprunt, dateRetourPrevue, dateRetour (null tant que le livre n'est pas rendu).
// Constructeur Emprunt(Livre, Adherent, LocalDate aujourdhui, int dureeJours) ;
// long rendre(LocalDate date) : clôture l'emprunt (ConflitException s'il l'est déjà) et renvoie
// le nombre de jours de retard (0 si à l'heure) ; getters.
public class Emprunt {
}
