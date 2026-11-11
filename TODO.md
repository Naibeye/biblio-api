# Chapitre 10 — Tester une application Spring (TP 7, 1 h 30)

Point de départ : la fin du chapitre 9. Les classes de test existent sous forme de squelettes.

- [ ] 0. Profil de test `src/test/resources/application-test.properties` : base H2
      `jdbc:h2:mem:test;DB_CLOSE_DELAY=-1`, et `data.sql` désactivé (`spring.sql.init.mode=never`).
- [ ] 1. `EmpruntServiceTest` (Mockito, horloge fixe au 1er mars 2026) : cinq tests au moins, dont
      les deux règles de refus — date de retour à 14 jours, livre déjà emprunté, limite atteinte,
      pénalité de 1 500 pour 3 jours de retard, enseignant (28 jours, pas de pénalité).
- [ ] 2. `LivreControllerTest` (`@WebMvcTest`) : 200, 201 avec `Location`, 400, 404, 409.
- [ ] 3. `LivreRepositoryTest` (`@DataJpaTest`) : `findByTitreContainingIgnoreCase("SPRING")`
      ne renvoie qu'un livre. `EmpruntRepositoryTest` : un livre rendu n'est plus « sorti ».
- [ ] 4. `ParcoursEmpruntTests` (`@SpringBootTest`) : créer un livre et un adhérent, emprunter (201),
      second emprunt du même livre (409), rendre (200), réemprunter (201).
      `ParcoursLivreTests` : créer puis relire un livre.
- [ ] 5. Introduire volontairement un bogue (durée 15 jours) et observer quels tests échouent.

Lancer : `./mvnw test`. Endroits à compléter : `grep -rn "TODO ch10" src`
