# HelsinkiCityBikeApp
This is the Helsinki City Bikes application made as an exercise. 
Based on Solita Dev Academy Finland pre-assignment.  https://github.com/solita/dev-academy-2023-exercise
Made with Java, Spring Boot, Thymeleaf and Bootstrap.

This application show the details of the bicycle journeys made in Helsinki City area. It also shows the details of the
stations which are located in different part of Helsinki.

## Technology choices
- Java Spring Boot
- PostgreSQL 14
- Maven
- Bootstrap 
- Docker
- HTML


### Index view
http://localhost:8080/
By clicking on the "Upload" button, the data will be loaded into our database. It usually takes 5-8 minutes


### Journey list view
http://localhost:8080/journeys
Lists journeys with pagination.
The journeys can be ordered by clicking on any of the table header cells.
A new journey can be added by clicking on "Add new journey"
You can also update information about the journey or delete the journey by clicking on the corresponding button.

### Station list view
http://localhost:8080/stations
Lists stations with pagination.
The stations can be ordered by clicking on any of the table header cells.
By clicking on a row "Station Id" or "Station name" on the station table, the single station view opens on that station.

### Single station view
http://localhost:8080/stations/show/1
Shows:
- the station name
- total number of journeys starting from the station
- total number of journeys ending at the station
- the average distance of a journey starting from the station
- the average distance of a journey ending to the station
- top 5 most popular departure stations for journeys ending at the station
- top 5 most popular return stations for journeys starting from the station

# Setting up and running the application
### 1. Prerequisite environment
- Developed and tested using:
    - Windows 10 Pro
    - Docker Desktop
    - Chrome browser



### 2. Configurations
Create a local PostgreSQL database called "HelsinkiBikeDatabase" with a username "postgres" and password "postgres" or change
the following settings from application.properties to match your configurations.

* spring.datasource.url=jdbc:postgresql://localhost:5432/HelsinkiBikeDatabase
* spring.datasource.username=postgres
* spring.datasource.password=postgres

### 2. How to build and run (without Docker)

Run the bat file:
``
out/artifacts/HelsinkiCityBikeApp_jar/run.bat
``
or run the jar file:
``
java -jar out/artifacts/HelsinkiCityBikeApp_jar/HelsinkiCityBikeApp.jar
``
Open http:/localhost:8080/ with browser.
