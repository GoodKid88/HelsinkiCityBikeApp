FROM openjdk
ADD out/artifacts/HelsinkiCityBikeApp_jar HelsinkiCityBikeApp.jar
ENTRYPOINT ["java", "-jar", "HelsinkiCityBikeApp.jar"]

#COPY . /src/main/java/ /tmp/
#WORKDIR /tmp
#RUN javac HelsinkiCityBikeAppApplication.java
#CMD ["java", "HelsinkiCityBikeAppApplication"]
