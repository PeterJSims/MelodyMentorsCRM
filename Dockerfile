FROM openjdk:17-jdk-slim
EXPOSE 8080
ADD target/*.jar demo.jar
ENTRYPOINT ["java","-jar","demo.jar"]



# docker run -p 8080:8080 melodymentorscrm