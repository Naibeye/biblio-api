# BiblioAPI — le fil rouge du cours Spring Boot (L3 TDSI, UCAD)

Ce dépôt contient le code de **BiblioAPI**, l'API de la bibliothèque universitaire construite
pas à pas pendant le cours *Développement d'applications avec Spring Boot*
(Ing. DJIMNAIBEYE Sidoine). Spring Boot 4.1, Java 17 ou plus.

## Récupérer le projet (une seule fois)

```bash
git clone https://github.com/Naibeye/biblio-api.git
cd biblio-api
```

La branche `main` est le point de départ du chapitre 3 : le projet généré par Initializr et les
TODO du TP 1.

## Avant chaque cours : récupérer la branche de début

Les chapitres 1 et 2 (rappels Java, HTTP, SQL, Maven, Git) et le TP 0 (installation) n'ont pas
de code BiblioAPI. Le projet commence au chapitre 3.

Avant chaque séance de TP, une **branche de début** est publiée sur GitHub. Elle part du code
complet du chapitre précédent et ajoute un fichier `TODO.md` (les étapes du TP) et des
commentaires `// TODO chXX` aux endroits à compléter. Le projet compile ; les méthodes à écrire
lèvent `UnsupportedOperationException`.

| Branche à récupérer | Chapitre | TP |
| --- | --- | --- |
| `chapitre-03-premier-projet-debut` | 3. Premier projet Spring Boot | TP 1 |
| `chapitre-04-injection-debut` | 4. Inversion de contrôle et injection de dépendances | TP 2 |
| `chapitre-05-configuration-debut` | 5. Configuration et profils | TP 2 bis |
| `chapitre-06-controleurs-debut` | 6. Contrôleurs REST | TP 3 |
| `chapitre-07-dto-validation-debut` | 7. DTO, validation et gestion des erreurs | TP 4 |
| `chapitre-08-jpa-debut` | 8. Spring Data JPA | TP 5 |
| `chapitre-09-relations-debut` | 9. Relations, transactions et pagination | TP 6 |
| `chapitre-10-tests-debut` | 10. Tester une application Spring | TP 7 |
| `chapitre-11-securite-debut` | 11. Spring Security et JWT, séance 1 | TP 8a |
| `chapitre-11-securite-tp8a` | 11. Spring Security et JWT, séance 2 | TP 8b |
| `chapitre-12-production-debut` | 12. Actuator, Flyway et Docker | TP 9 |
| `chapitre-13-projet-debut` | 13. Projet de synthèse | projet |

Exemple pour le chapitre 8 :

```bash
git add -A && git commit -m "TP 4 : mon travail"   # 1. enregistrer le travail du TP précédent
git fetch origin                                    # 2. récupérer les branches publiées
git switch chapitre-08-jpa-debut                    # 3. se placer sur la branche de début
git switch -c mon-tp5                               # 4. travailler sur une branche à soi
```

À l'étape 3, `git switch` crée la branche locale qui suit celle de GitHub. S'il répond
`invalid reference`, la branche n'est pas encore publiée.

On travaille sur sa propre branche (`mon-tp5`) pour garder la branche de début intacte. Si
elle est corrigée après sa publication, on récupère la correction ainsi :

```bash
git switch chapitre-08-jpa-debut && git pull
git switch mon-tp5 && git merge chapitre-08-jpa-debut
```

## Pendant le TP

```bash
grep -rn "TODO ch08" src              # retrouver les endroits à compléter
./mvnw test                           # vérifier
```

## Comparer avec le corrigé

Le corrigé d'un chapitre arrive avec la branche de début du chapitre suivant : le commit qui
précède cette branche (`~1`) est la fin du chapitre précédent.

```bash
git fetch origin
git diff mon-tp5 origin/chapitre-09-relations-debut~1   # mon TP 5 face au corrigé du chapitre 8
```

Pour le TP 8a, le corrigé est la branche `origin/chapitre-11-securite-tp8a` elle-même (elle
contient aussi les TODO du TP 8b).

## Lancer l'application

```bash
./mvnw test
./mvnw spring-boot:run                                       # chapitres 3 à 7
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev        # à partir du chapitre 8
```
