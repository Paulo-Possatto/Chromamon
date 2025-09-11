FROM maven:3.9.9-eclipse-temurin-21 AS build

WORKDIR /app

COPY pom.xml .

RUN mvn dependency:go-offline -B

COPY src ./src

RUN mvn package -DskipTests --no-transfer-progress

FROM eclipse-temurin:21-jre-jammy

WORKDIR /app

RUN apt-get update && \
    apt-get install -y curl && \
    rm -rf /var/lib/apt/lists/* && \
    mkdir -p /app/ssl /app/config /app/logs

COPY entrypoint.sh /entrypoint.sh
RUN chmod +x /entrypoint.sh

RUN addgroup --system spring && adduser --system spring --ingroup spring

RUN chown -R spring:spring /app/ssl /app/logs

USER spring:spring

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8443

ENTRYPOINT ["/entrypoint.sh"]