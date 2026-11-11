package sn.ucad.biblioapi.securite;

// TODO ch11 (TP 8a, étape 3) : @Configuration + @EnableWebSecurity, avec deux beans :
//   SecurityFilterChain — sans CSRF ni session (STATELESS), puis, dans l'ordre :
//     1. ouvert à tous : /error, /actuator/health et /actuator/health/**, Swagger
//        (/swagger-ui.html, /swagger-ui/**, /v3/api-docs/**), GET /api/livres/** et /api/auteurs/**
//     2. être connecté : POST /api/auth/jeton, GET /api/moi/**
//     3. rôle BIBLIOTHECAIRE : /api/emprunts/retards, puis /api/livres/**, /api/auteurs/**,
//        /api/adherents/** et /actuator/** (toute autre méthode, PATCH compris)
//     4. être connecté : /api/emprunts/**
//     5. tout le reste : denyAll()
//     et httpBasic.
//   PasswordEncoder — BCryptPasswordEncoder.
public class SecuriteConfig {
}
