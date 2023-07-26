#
# Build stage
#
FROM maven:3.8.0 AS build
COPY . .
RUN mvn clean package -DskipTests

#
# Package stage
#
FROM openjdk:8-jdk-alpine
COPY --from=build /target/todolist-0.0.1-SNAPSHOT.jar todolist.jar
# ENV PORT=8080
EXPOSE 8080
ENTRYPOINT ["java","-jar","todolist.jar"]
