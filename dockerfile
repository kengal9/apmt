FROM maven:3.8.3-openjdk-17 AS builder
COPY . /usr/src/app
WORKDIR /usr/src/app
RUN mvn clean install -Dmaven.test.skip=true

FROM openjdk:17
COPY --from=builder /usr/src/app/target/*.jar /usr/src/app/my-app.jar
CMD ["java", "-jar", "-Dforeground=false", "/usr/src/app/my-app.jar"]