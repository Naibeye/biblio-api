# Chapitre 12 — Vers la production : Actuator, Flyway et Docker (TP 9, 1 h 30)

Point de départ : la fin du chapitre 11.

- [ ] 1. Journaliser en `INFO` chaque emprunt et chaque retour dans `EmpruntService`
      (jamais un mot de passe ni un jeton).
- [ ] 2. Ajouter Actuator : exposer `health` et `info` (`management.endpoints.web.exposure.include`,
      `management.info.env.enabled=true`, `info.application.nom` / `version`). La sécurité ouvre
      déjà `/actuator/health/**` et réserve le reste aux bibliothécaires. Arrêter la base et observer le statut.
- [ ] 3. Écrire le script Flyway `db/migration/V1__schema_initial.sql` (tables `auteur`, `livre`,
      `adherent`, `emprunt`, `utilisateur`). Déplacer `data.sql` dans `db/demo/V100__donnees_de_demo.sql`,
      chargé seulement en dev (`spring.flyway.locations`). `ddl-auto=validate` partout.
      En prod, couper Swagger (`springdoc.api-docs.enabled=false`, `springdoc.swagger-ui.enabled=false`).
- [ ] 4. Écrire le `Dockerfile` (deux étapes, utilisateur non root), `.dockerignore`, `compose.yaml`
      (PostgreSQL 16 + l'API en profil prod) et `.env.example` ; `docker compose up --build` ;
      rejouer les requêtes `curl` du parcours d'emprunt.
- [ ] 5. Redémarrer les conteneurs et vérifier que les données sont conservées (volume).

Contrôles attendus :
```
GET /actuator/health (anonyme) -> {"groups":["liveness","readiness"],"status":"UP"}
GET /actuator/info   (moussa)  -> {"application":{"nom":"BiblioAPI","version":"1.0.0"}}
GET /actuator/info   (anonyme) -> 401
```

Endroits à compléter : `grep -rn "TODO ch12" . --include=*.java --include=*.properties --include=*.sql --include=*.xml --include=Dockerfile --include=*.yaml`
