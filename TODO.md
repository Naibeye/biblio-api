# Chapitre 11 — Sécuriser l'API : Spring Security et JWT (2 séances)

Point de départ : la fin du chapitre 10.

## Séance 1 — TP 8a : sécuriser BiblioAPI avec des rôles (1 h)
- [ ] 1. Ajouter `spring-boot-starter-security` et `spring-boot-starter-security-test` ;
      constater que tout renvoie 401, y compris certains tests du TP 7.
- [ ] 2. Écrire `Role`, l'entité `Utilisateur`, son dépôt et `UtilisateursEnBase`
      (`UserDetailsService`) ; insérer les comptes de démonstration dans `data.sql`
      (empreintes BCrypt fournies en commentaire).
- [ ] 3. Écrire `SecuriteConfig` : refuser par défaut (`denyAll()`), catalogue en lecture ouvert,
      gestion réservée au rôle BIBLIOTHECAIRE, emprunts pour les personnes connectées, `httpBasic`,
      BCrypt. Vérifier au `curl` les cas 200, 401, 403, 204 ; un adhérent reçoit 403 sur
      `POST /api/auteurs`. Ouvrir la console H2 en dev (`ConsoleH2Config`).
- [ ] 4. Retirer `adherentId` de `EmpruntRequete` : l'adhérent est la personne connectée.
      Amina ne peut pas rendre l'emprunt d'un autre (404) ; ajouter `GET /api/moi/emprunts`.
- [ ] 5. Adapter les tests du TP 7 (`@WithMockUser`, `@ActiveProfiles("test")`,
      `@Import(SecuriteConfig.class)`) ; ajouter les trois tests 401 / 403 / 204.

Comptes de démonstration : `amina@ucad.sn` / `amina-2026` (adhérente),
`moussa@ucad.sn` / `moussa-2026` (bibliothécaire), `hassan@ucad.sn` / `hassan-2026` (adhérent).

## Séance 2 — TP 8b : des jetons JWT (1 h 15)
Point de départ : la fin du TP 8a (étiquette `ch11-tp8a`). Les TODO de cette séance y sont posés.

Endroits à compléter : `grep -rn "TODO ch11" src pom.xml`
