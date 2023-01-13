FROM maven:3.8.7-openjdk-18-slim AS build
WORKDIR /app
COPY . ./
RUN mvn clean package -DskipTests

FROM amazoncorretto:17-alpine3.16
RUN mkdir /app
COPY --from=build /app/target/backend-0.0.1-SNAPSHOT.jar /app/application.jar
ENTRYPOINT ["java","-jar","/app/application.jar"]


#ARG JAR_FILE=target/*.jar
#COPY ${JAR_FILE} app.jar
#ENTRYPOINT ["java","-jar","/app.jar"]
