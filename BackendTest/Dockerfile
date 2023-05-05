FROM eclipse-temurin:17-jdk-alpine AS buildenv
WORKDIR app
COPY pom.xml .
RUN ["mvn", "install"]
COPY src ./src
RUN ["mvn", "package"]

FROM eclipse-temurin:17-jre-alpine as publishenv
WORKDIR publish
COPY --from=buildenv