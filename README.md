# BiblioAPI — le fil rouge du cours Spring Boot (L3 TDSI, UCAD)

Ce dépôt contient le code de **BiblioAPI**, l'API de la bibliothèque universitaire construite
pas à pas pendant le cours *Développement d'applications avec Spring Boot*
(Ing. DJIMNAIBEYE Sidoine). Spring Boot 4.1, Java 17 ou plus.

## Une branche par chapitre

Les chapitres 1 et 2 (rappels Java, HTTP, SQL, Maven, Git) et le TP 0 (installation) n'ont pas
de code BiblioAPI. Le projet commence au chapitre 3.

| Branche | Chapitre | TP |
| --- | --- | --- |
| `chapitre-03-premier-projet` | 3. Premier projet Spring Boot | TP 1 |
| `chapitre-04-injection` | 4. Inversion de contrôle et injection de dépendances | TP 2 |
| `chapitre-05-configuration` | 5. Configuration et profils | TP 2 bis |
| `chapitre-06-controleurs` | 6. Contrôleurs REST | TP 3 |
| `chapitre-07-dto-validation` | 7. DTO, validation et gestion des erreurs | TP 4 |
| `chapitre-08-jpa` | 8. Spring Data JPA | TP 5 |
| `chapitre-09-relations` | 9. Relations, transactions et pagination | TP 6 |
| `chapitre-10-tests` | 10. Tester une application Spring | TP 7 |
| `chapitre-11-securite` | 11. Spring Security et JWT | TP 8a, TP 8b |
| `chapitre-12-production` | 12. Actuator, Flyway et Docker | TP 9 |
| `chapitre-13-projet` | 13. Projet de synthèse | projet |

Chaque branche part de la fin du chapitre précédent et contient deux commits :

1. **début du chapitre** : le point de départ du TP, avec un fichier `TODO.md` (les étapes du TP)
   et des commentaires `// TODO chXX` aux endroits à compléter. Le projet compile ; les
   méthodes à écrire lèvent `UnsupportedOperationException`.
2. **fin du chapitre** : le code complet (corrigé du TP), `./mvnw test` au vert.

Le chapitre 11 a un commit intermédiaire de plus : la fin de la séance 1 (TP 8a), qui est le
point de départ de la séance 2 (TP 8b).

Des étiquettes marquent ces commits : `ch05-debut`, `ch05-fin`, `ch11-tp8a`, etc.
La branche `main` contient l'état final (fin du chapitre 13).

## Travailler sur un chapitre

```bash
git switch -c mon-tp5 ch08-debut      # partir du début du chapitre 8 sur une branche à soi
grep -rn "TODO ch08" src              # retrouver les endroits à compléter
./mvnw test                           # vérifier
git diff ch08-debut ch08-fin          # comparer avec le corrigé (après avoir essayé !)
```

## Lancer l'application

```bash
./mvnw test
./mvnw spring-boot:run                                       # chapitres 3 à 7
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev        # à partir du chapitre 8
```
