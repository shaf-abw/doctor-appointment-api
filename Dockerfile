# Runtime stage
FROM azul/zulu-openjdk:17
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} docker-appointment-api.jar
ENTRYPOINT ["java","-jar","/docker-appointment-api.jar"]
EXPOSE 8080
