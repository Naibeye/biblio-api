# Chapitre 6 — Contrôleurs REST (cours + TP 3, 1 h 30)

Point de départ : la fin du chapitre 5. Les livres reçoivent un identifiant numérique `id`
attribué par le serveur : `Livre` et le dépôt en mémoire `LivreRepository` sont fournis (mêmes
noms de méthodes que Spring Data, pour faciliter le chapitre 8).

## Pendant le cours : les livres
- [ ] Compléter `LivreService` (lister, rechercher par titre, lire, créer, remplacer, supprimer).
- [ ] Compléter `LivreController` sur `/api/livres` : `GET` (filtre `?titre=` facultatif),
      `GET /{id}` (200 ou 404), `POST` (201 + en-tête `Location`), `PUT /{id}` (200 ou 404),
      `DELETE /{id}` (204 ou 404). Tester chaque opération avec `curl -i`.

## TP 3 : l'API des auteurs
- [ ] 1. Sur le modèle des livres : `AuteurRepository` (en mémoire), `AuteurService` et
      `AuteurController` sur `/api/auteurs` avec les cinq opérations (`Auteur` est fourni).
      Tester chaque opération avec `curl -i` et noter le code de statut obtenu.
- [ ] 2. Ajouter `GET /api/auteurs?nationalite=senegalaise`.
- [ ] 3. Ajouter `PATCH /api/auteurs/{id}` qui ne modifie que le nom (corps : `{"nom": "..."}`).
- [ ] 4. Bonus : un script shell qui enchaîne toutes les requêtes et affiche les codes.

Endroits à compléter : `grep -rn "TODO ch06" src`
