
FROM amazoncorretto:17 as build

COPY target/ggau-0.0.1-SNAPSHOT.jar ggau-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/ggau-0.0.1-SNAPSHOT.jar"]