# Chapitre 7 — DTO, validation et gestion des erreurs (TP 4, 2 h)

Point de départ : la fin du chapitre 6.

- [ ] 1. Introduire `LivreRequete` (ce que le client envoie : pas d'id) et `LivreReponse` (ce que
      l'API renvoie : l'id et un booléen `recent`, vrai à partir de 2020). Le contrôleur ne
      manipule plus `Livre` directement ; il passe au service des valeurs simples.
- [ ] 2. Ajouter les contraintes de validation sur `LivreRequete` (ISBN : chiffres et tirets, 10 à
      17 caractères ; titre obligatoire, 200 caractères au plus ; année obligatoire, pas avant 1450)
      et `@Valid` dans le contrôleur. Un titre vide doit donner un 400 détaillé.
- [ ] 3. Créer `RessourceIntrouvableException`, `ConflitException` et le `GestionnaireErreurs`
      (`@RestControllerAdvice`, réponses `ProblemDetail`). Le service lève les exceptions :
      livre inconnu → 404 ; ISBN déjà utilisé → 409.
- [ ] 4. Installer Swagger UI (`http://localhost:8080/swagger-ui.html`) et tester chaque endpoint.
- [ ] 5. Appliquer la même démarche aux auteurs : `AuteurRequete`, `AuteurReponse`,
      `ChangementNom` validé, exceptions dans `AuteurService`.

Contrôles attendus :
```
POST {"isbn":"","titre":"","annee":1200}   -> 400, "erreurs" : isbn, titre, annee
GET  /api/livres/99                        -> 404, "detail": "Livre 99 introuvable"
POST deux fois {"isbn":"978-2-4242",...}   -> 201 puis 409
POST sans Content-Type                     -> 415
```

Endroits à compléter : `grep -rn "TODO ch07" src pom.xml`
