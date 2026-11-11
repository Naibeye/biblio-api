# Chapitre 4 — Inversion de contrôle et injection de dépendances (TP 2, 1 h 30)

Point de départ : la fin du chapitre 3. Les classes à écrire existent déjà sous forme de
squelettes : elles compilent, mais ne sont pas encore des beans et leurs méthodes lèvent
`UnsupportedOperationException`.

- [ ] 1. Paquetage `sn.ucad.biblioapi.livre` : `Livre` (fourni), `LivreRepository` (en mémoire,
      `@PostConstruct` pour trois livres d'exemple), `LivreService`, `LivreController`.
      Un journal dans chaque constructeur.
- [ ] 2. Lancer ; vérifier l'ordre de création dans le journal, puis `curl http://localhost:8080/api/livres`.
- [ ] 3. Écrire `InspectionConteneur` : combien de beans au total ? lesquels sont à vous ?
- [ ] 4. Ajouter `GET /api/livres/instance` : un seul `LivreService` existe-t-il ?
- [ ] 5. `commun/HorlogeConfig` déclare un bean `Clock` ; `GET /api/emprunts/date-retour` renvoie
      la date de retour prévue (aujourd'hui + 14 jours).
- [ ] 6. Retirer volontairement `@Service` de `LivreService` : lire l'erreur au démarrage, remettre.
- [ ] 7. `notification/` : rendre `NotificateurEmail` et `NotificateurSms` beans, écrire
      `RelanceService` et `GET /api/relances/essai?destinataire=...`. Observer l'erreur
      « required a single bean, but 2 were found », puis la corriger avec `@Primary`, puis avec `@Qualifier`.
- [ ] 8. Ajouter `@PreDestroy` dans le dépôt ; arrêter l'application et retrouver la ligne du journal.

Endroits à compléter : `grep -rn "TODO ch04" src`
