FROM openjdk:17
ADD target/renting-system.jar renting-system.jar
ENTRYPOINT ["java", "-jar", "/renting-system.jar"]