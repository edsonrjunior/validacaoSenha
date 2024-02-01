FROM openjdk:21-oracle AS build
WORKDIR /app
COPY ./target/validacao_senha-0.0.1-SNAPSHOT.jar /app
EXPOSE 8080
CMD ["java", "-jar", "validacao_senha-0.0.1-SNAPSHOT.jar"]