# Chapitre 3 — Premier projet Spring Boot (TP 1, 1 h 30)

Point de départ : le projet tel que le génère Spring Initializr (Maven, Java 17, Spring Boot 4.1,
groupe `sn.ucad`, artefact `biblio-api`, dépendances *Spring Web* et *Spring Boot DevTools*).

- [ ] 1. Lancer le projet (`./mvnw spring-boot:run`) ; constater la 404 sur `http://localhost:8080`.
      Repérer dans le journal le port, la version de Tomcat et la durée de démarrage.
- [ ] 2. Afficher l'arbre des dépendances (`./mvnw dependency:tree`) : retrouver Tomcat, Jackson, Logback.
- [ ] 3. Compléter `BonjourController` : `GET /bonjour` et `GET /bonjour/perso?nom=...` ; tester avec `curl`.
- [ ] 4. Ajouter `GET /info` (le record `Info` est fourni) ; observer le format de la date, puis
      les en-têtes avec `curl -i`.
- [ ] 5. Ajouter `GET /bonjour/{nom}` (`@PathVariable`).
- [ ] 6. Ajouter un `Logger` ; activer `DEBUG` pour `sn.ucad` dans `application.properties`.
- [ ] 7. Changer le port en 8081, vérifier, revenir à 8080. Lancer deux fois l'application.
- [ ] 8. `./mvnw package` puis `java -jar target/biblio-api-0.0.1-SNAPSHOT.jar`.

Endroits à compléter : `grep -rn "TODO ch03" src`
