#
# Build stage
#
FROM maven:3.8.3-openjdk-17 AS build
COPY . .
RUN mvn clean package

#
# Package stage
#
FROM openjdk:17-oracle
EXPOSE 8080
COPY --from=build target/todolist.jar todolist.jar
ENTRYPOINT [ "java","-jar","todolist.jar"]
