FROM maven:3.9.2-eclipse-temurin-17-alpine AS buildenv
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN ["mvn", "install"]
RUN ["mvn", "clean", "package"]
WORKDIR /app/target
EXPOSE 8082

FROM eclipse-temurin:17.0.7_7-jre-alpine AS publishenv
WORKDIR /publish
COPY --from=buildenv /app/target/BackendTest-0.0.1-SNAPSHOT.jar .
EXPOSE 8082
ENTRYPOINT [ "java", "-jar", "BackendTest-0.0.1-SNAPSHOT.jar"]