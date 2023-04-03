FROM openjdk
ADD target/HelsinkiCityBikeApp-0.0.1-SNAPSHOT.jar HelsinkiCityBikeApp-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/HelsinkiCityBikeApp-0.0.1-SNAPSHOT.jar"]
