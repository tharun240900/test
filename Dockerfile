FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim
COPY --from=build /target/aws-mysql-0.0.1-SNAPSHOT.jar aws-mysql.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "aws-mysql.jar"]
