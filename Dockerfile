# Etapa 1: Construção do projeto
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app

# Copia o arquivo pom.xml e o diretório src
COPY pom.xml .
COPY src ./src

# Compila o projeto e gera o pacote (JAR)
RUN mvn clean package -DskipTests

# Etapa 2: Imagem para rodar o JAR
FROM openjdk:17-jdk-slim
WORKDIR /app

# Copia o JAR gerado da etapa de construção
COPY --from=build /app/target/*.jar app.jar

# Expõe a porta padrão do Spring Boot
EXPOSE 8080

# Comando para iniciar o aplicativo
ENTRYPOINT ["java", "-jar", "app.jar"]
