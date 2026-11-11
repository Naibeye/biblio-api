# TODO ch12 (TP 9, étape 4) : deux étapes.
#   1. construction (eclipse-temurin:17-jdk) : copier mvnw, pom.xml et .mvn, télécharger les
#      dépendances (dependency:go-offline), puis copier src et faire le package sans les tests ;
#   2. exécution (eclipse-temurin:17-jre) : un utilisateur système « appli » (pas root), le jar
#      copié depuis l'étape 1, port 8080, ENTRYPOINT java -jar.
