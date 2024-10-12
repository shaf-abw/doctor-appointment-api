# Build stage
FROM maven:3.8.7-openjdk-18 AS build
WORKDIR /build
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

# Runtime stage
FROM azul/zulu-openjdk:17
ARG APP_VERSION=1.0.0

WORKDIR /app
COPY --from=build /build/target/docker-appointment-api-*.jar /app/

EXPOSE 8088

ENV JAR_VERSION=${APP_VERSION}

CMD java -jar book-network-${JAR_VERSION}.jar