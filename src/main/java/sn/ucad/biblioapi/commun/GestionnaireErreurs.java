package sn.ucad.biblioapi.commun;

// TODO ch07 (TP 4, étape 3) : traduire les exceptions en réponses HTTP, en un seul endroit.
//   - hériter de ResponseEntityExceptionHandler (415, JSON illisible... au format ProblemDetail)
//   - RessourceIntrouvableException -> 404 ; ConflitException -> 409
//   - échec de @Valid (handleMethodArgumentNotValid) -> 400 avec une propriété « erreurs » :
//     { champ : premier message }
//   - toute autre exception -> 500 « Erreur interne » (le détail va dans le journal, pas au client)
public class GestionnaireErreurs {
}
