# OpenJDK 17 imajını kullan
FROM openjdk:17-jdk-slim

# JAR dosyasını konteynıra kopyala
COPY target/*.jar app.jar

# Uygulamayı çalıştır
ENTRYPOINT ["java", "-jar", "/app.jar"]
