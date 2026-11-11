package sn.ucad.biblioapi.securite;

// TODO ch11 (TP 8b, étape 2) : classe de configuration.
//   - constante EMETTEUR = "biblio-api" ;
//   - le constructeur lit biblio.jwt.secret (@Value) et refuse une clé de moins de 32 octets
//     (IllegalStateException) ; il en fait une SecretKeySpec "HmacSHA256" ;
//   - beans : JwtEncoder (NimbusJwtEncoder), JwtDecoder (NimbusJwtDecoder.withSecretKey, avec
//     JwtValidators.createDefaultWithIssuer(EMETTEUR)), JwtAuthenticationConverter (rôles lus dans
//     la revendication « roles », préfixés par ROLE_).
public class JwtConfig {
}
