package sn.ucad.biblioapi.securite;

// TODO ch11 (TP 8b, étape 2) : contrôleur REST — POST /api/auth/jeton pour une personne déjà
// authentifiée par mot de passe (403 si elle présente un jeton). Le jeton : émetteur
// JwtConfig.EMETTEUR, sujet = identifiant, émis maintenant (Clock), expire dans 1 h, revendication
// « roles » (sans le préfixe ROLE_), signé en HS256. Réponse : {"jeton": "..."}.
public class JetonController {
}
