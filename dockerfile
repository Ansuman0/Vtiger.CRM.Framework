#FROM openjdk:17
#EXPOSE 8080
#COPY Vtiger.CRM.Framework-1.0-SNAPSHOT.jar /app/Vtiger.CRM.Framework-1.0-SNAPSHOT.jar
#ENTRYPOINT ["java", "-jar", "/app/Vtiger.CRM.Framework-1.0-SNAPSHOT.jar]

# Use Maven for the build stage
FROM maven:3.8.4-openjdk-17-slim AS build
WORKDIR /app
COPY ./pom.xml ./pom.xml
RUN mvn dependency:go-offline -B
COPY ./src ./src
RUN mvn package
FROM openjdk:17
WORKDIR /app
EXPOSE 8080
COPY --from=build /app/target/Vtiger.CRM.Framework-1.0-SNAPSHOT.jar /app/Vtiger.CRM.Framework-1.0-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/app/Vtiger.CRM.Framework-1.0-SNAPSHOT.jar"]
