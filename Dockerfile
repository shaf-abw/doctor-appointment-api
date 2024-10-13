# Runtime stage
FROM azul/zulu-openjdk:17
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} doctor-appointment-api.jar
ENTRYPOINT ["java","-jar","/doctor-appointment-api.jar"]
EXPOSE 8080
