# Chapitre 11 — Séance 2 — TP 8b : des jetons JWT (1 h 15)

Point de départ : la fin du TP 8a (authentification Basic, rôles, utilisateurs en base).
Les règles d'accès ne changent pas ; on ajoute une seconde manière de s'authentifier.

- [ ] 1. Ajouter `spring-boot-starter-security-oauth2-resource-server` (et son `-test`) ; définir
      `JWT_SECRET` (ou utiliser la clé de repli du profil dev). Clé : `biblio.jwt.secret`.
- [ ] 2. Écrire `JwtConfig` (clé HMAC de 32 octets au moins, encodeur, décodeur qui vérifie
      l'émetteur `biblio-api`, rôles lus dans la revendication `roles`) et `JetonController`
      (`POST /api/auth/jeton`, valable 1 h) ; ajouter `oauth2ResourceServer` à la chaîne de filtres.
- [ ] 3. Obtenir un jeton (`curl -u moussa@ucad.sn:moussa-2026 -X POST .../api/auth/jeton`) ;
      décoder son contenu avec `base64 -d` ; l'utiliser pour emprunter (`-H "Authorization: Bearer ..."`).
- [ ] 4. Vérifier qu'un jeton présenté à `/api/auth/jeton` est refusé (403), qu'un jeton modifié
      au milieu du contenu est refusé (401), et qu'une clé de 10 caractères empêche le démarrage.
- [ ] 5. Mettre à jour les tests (`@Import({SecuriteConfig.class, JwtConfig.class})`, clé de test) ;
      `./mvnw test` doit être vert.

Endroits à compléter : `grep -rn "TODO ch11" src pom.xml`
