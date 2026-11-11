package sn.ucad.biblioapi.emprunt;

import sn.ucad.biblioapi.adherent.Categorie;

// TODO ch05 (TP 2 bis, étape 1) : lier ce record aux clés « biblio.emprunt.* » et le faire valider
// au démarrage ; activer la lecture de ces records dans BiblioApiApplication.
public record EmpruntProperties(Regle etudiant, Regle enseignant) {

    // TODO ch05 : contraintes — durée et maximum strictement positifs, pénalité positive ou nulle ;
    // les deux règles sont obligatoires et doivent elles-mêmes être validées.
    public record Regle(int dureeJours, int maxEmprunts, double penaliteParJour) { }

    public Regle pour(Categorie categorie) {
        throw new UnsupportedOperationException("TODO ch05 : la règle de cette catégorie");
    }
}
