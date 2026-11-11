# Chapitre 5 — Configuration et profils (TP 2 bis, 45 min)

Point de départ : la fin du chapitre 4.

- [ ] 1. Ajouter `spring-boot-starter-validation` au `pom.xml`. Compléter `EmpruntProperties`
      (règle d'emprunt de chaque catégorie, clés `biblio.emprunt.*`, validées au démarrage) et
      activer `@ConfigurationPropertiesScan`. Écrire `RegleController` :
      `GET /api/regles/{categorie}` (exercice 5.1). Faire utiliser les règles par `EmpruntService` :
      `GET /api/emprunts/date-retour?categorie=ENSEIGNANT`.
- [ ] 2. Créer `application-dev.properties` (journal `DEBUG`, durée étudiant 1 jour) et
      `application-prod.properties` (journal `INFO`).
- [ ] 3. Lancer avec chacun des deux profils, puis sans profil ; comparer la troisième ligne du
      journal et `GET /api/regles/ETUDIANT`.
- [ ] 4. Écrire volontairement `dure-jours` (faute de frappe) : lire l'erreur au démarrage, corriger.
- [ ] 5. `SERVER_PORT=9090` dans la configuration de lancement : quel port ? Puis `--server.port=7000`.
- [ ] 6. Passer `InspectionConteneur` en `@Profile("dev")`.
- [ ] 7. Rendre les deux notificateurs conditionnels (`@ConditionalOnProperty` sur
      `biblio.notification.canal`, email par défaut) ; changer de canal par la ligne de commande,
      puis par une variable d'environnement.

Endroits à compléter : `grep -rn "TODO ch05" src pom.xml`
