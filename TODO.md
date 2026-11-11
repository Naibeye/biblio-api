# Chapitre 13 — Projet de synthèse BiblioAPI

Point de départ : la fin du chapitre 12. Le cahier des charges complet (acteurs, récits
utilisateurs, endpoints, exigences, jalons, grille) est au chapitre 13 du polycopié.

## Jalons
| Jalon | Chapitres | Contenu | État au départ |
| --- | --- | --- | --- |
| 1 | 3 à 7 | livres et auteurs : CRUD, DTO, validation, erreurs, Swagger | fait |
| 2 | 8, 9 | JPA ; adhérents ; emprunts et retours transactionnels ; pagination | fait |
| 3 | 10 | tests : services (Mockito), contrôleurs (MockMvc), un parcours complet | fait (20 tests) |
| 4 | 11 | sécurité : utilisateurs en base, rôles, JWT ; tests de sécurité | fait |
| 5 | 12 | Flyway, profils, Actuator, Docker Compose ; README d'installation | README à écrire |

## À faire pour livrer
- [ ] Retirer le code de démonstration des chapitres 3 à 5, qui ne fait pas partie de l'API :
      `BonjourController`, `Info`, `InspectionConteneur`, `emprunt/RegleController`, le paquetage
      `notification` (avec la sécurité, ces adresses ne répondent plus que 401/403 de toute façon).
- [ ] Livrable : un `README.md` — lancer en dev, lancer avec Docker, comptes de démonstration.
- [ ] Livrable : une collection de requêtes (fichier `.http`) qui déroule le parcours principal.
- [ ] Vérifier : `./mvnw test` au vert (au moins 15 tests), `docker compose up --build`.

## Pour aller plus loin (non couvert par le corrigé)
- Récit 2 : la disponibilité d'un livre dans `GET /api/livres/{id}`.
- Récit 9 (bonus) : `GET /api/statistiques` — livres les plus empruntés, emprunts du mois.

Endroits signalés : `grep -rn "TODO ch13" src`
