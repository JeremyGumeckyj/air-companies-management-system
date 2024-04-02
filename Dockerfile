FROM openjdk:16
COPY target/air-companies-management-system-1.0-SNAPSHOT.jar air-companies-management-system-1.0.jar
EXPOSE 8080
ENTRYPOINT ["java","-Dspring.profiles.active=docker","-jar","/air-companies-management-system-1.0.jar"]
