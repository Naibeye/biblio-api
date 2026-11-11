# Chapitre 8 — Spring Data JPA (TP 5, 2 h)

Point de départ : la fin du chapitre 7 (livres et auteurs en mémoire).

- [ ] 1. Ajouter les dépendances JPA, H2 et console H2 (voir le `pom.xml`) ; régler le profil dev
      (base `jdbc:h2:mem:biblio`, console H2, SQL affiché, `ddl-auto=create-drop`).
      Transformer `Livre` en entité ; remplacer le dépôt en mémoire par
      `LivreRepository extends JpaRepository<Livre, Long>`. Le service et `LivreReponse` passent aux getters.
- [ ] 2. Vérifier que tous les tests `curl` du TP 4 donnent les mêmes résultats.
- [ ] 3. Observer le SQL dans le journal pour chaque requête HTTP.
- [ ] 4. Remplacer la recherche par titre par `findByTitreContainingIgnoreCase`.
- [ ] 5. Ajouter `src/main/resources/data.sql` avec dix livres
      (`spring.jpa.defer-datasource-initialization=true`) ; explorer la console H2
      (`http://localhost:8080/h2-console`, URL `jdbc:h2:mem:biblio`, utilisateur `sa`).
- [ ] 6. Faire de même pour les adhérents (exercice 8.2) et exposer `/api/adherents`
      (`GET`, `POST` validé). Contrôles : 201, puis 409 pour le même email, 400 pour
      `"categorie":"VISITEUR"`.
- [ ] Profil prod : pilote PostgreSQL et `ddl-auto=validate` (les tables viendront de Flyway au chapitre 12).

Les auteurs restent en mémoire jusqu'au chapitre 9.

Endroits à compléter : `grep -rn "TODO ch08" src pom.xml`
