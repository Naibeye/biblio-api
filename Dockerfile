# Étape 1 : construire le jar (JDK). Les dépendances d'abord, pour profiter du cache Docker.
FROM eclipse-temurin:17-jdk AS construction
WORKDIR /app
COPY mvnw pom.xml ./
COPY .mvn .mvn
RUN chmod +x mvnw && ./mvnw -q dependency:go-offline
COPY src src
RUN ./mvnw -q clean package -DskipTests

# Étape 2 : une image légère (JRE), qui ne tourne pas en root.
FROM eclipse-temurin:17-jre
RUN groupadd --system appli && useradd --system --gid appli --no-create-home appli
WORKDIR /app
COPY --from=construction /app/target/*.jar app.jar
USER appli
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
