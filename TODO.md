# Chapitre 9 — Relations, transactions et pagination (TP 6, 2 h)

Point de départ : la fin du chapitre 8 (livres et adhérents en base, auteurs encore en mémoire).

- [ ] 1. Faire d'`Auteur` une entité (id, nom, nationalité) et d'`AuteurRepository` un dépôt JPA ;
      relier `Livre` à `Auteur` (`@ManyToOne(fetch = LAZY)`, colonne `auteur_id`, peut rester vide).
      Compléter `data.sql` : cinq auteurs, l'auteur de chaque livre, deux adhérents
      (Amina, étudiante, `amina@ucad.sn` ; Hassan, enseignant, `hassan@ucad.sn`).
- [ ] 2. Écrire l'entité `Emprunt`, son dépôt, `EmpruntReponse`, puis `EmpruntService`
      (`emprunter`, `rendre`, transactionnels) et le contrôleur :
      `POST /api/emprunts` (corps : `livreId`, `adherentId`) et `POST /api/emprunts/{id}/retour`.
- [ ] 3. Vérifier les règles : livre déjà sorti → 409 ; quatrième emprunt d'un étudiant → 409 ;
      sixième emprunt d'un enseignant → 409 ; retour en retard → pénalité dans la réponse.
- [ ] 4. Avec `show-sql`, lister les livres avec leur auteur, compter les requêtes (problème N+1),
      puis corriger avec `@EntityGraph`.
- [ ] 5. Paginer `GET /api/livres` (`PageReponse`, fourni), avec les filtres facultatifs `titre`,
      `auteur`, `annee`. `?page=0&size=3` → 3 éléments, `"nombrePages":4` ; `?sort=inconnu` → 400.
- [ ] Exercice 9.2 : `GET /api/emprunts/retards`.

Réglages : `spring.jpa.open-in-view=false` ; les services construisent les DTO **dans** la
transaction. Le profil dev reprend la durée normale (14 jours) pour les contrôles du TP.

Endroits à compléter : `grep -rn "TODO ch09" src`
