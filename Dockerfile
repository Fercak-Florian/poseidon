# ==================== STAGE 1 : BUILD ====================
FROM maven:3.9-eclipse-temurin-17-alpine AS build

# Répertoire de travail pour le build
WORKDIR /build

# On copie d'abord le pom.xml seul (optimisation du cache Docker)
COPY pom.xml .

# Téléchargement des dépendances (couche mise en cache si pom.xml ne change pas)
RUN mvn dependency:go-offline

# Copie du code source
COPY src ./src

# Compilation et packaging (sans les tests)
RUN mvn package -DskipTests

# ==================== STAGE 2 : RUN ====================
FROM eclipse-temurin:17-jdk-alpine

RUN mkdir /app

# On récupère uniquement le JAR produit par le stage précédent
COPY --from=build /build/target/poseidon-0.0.1-SNAPSHOT.jar /app/

WORKDIR /app

EXPOSE 8087

CMD ["java", "-jar", "poseidon-0.0.1-SNAPSHOT.jar", "--spring.profiles.active=prod"]