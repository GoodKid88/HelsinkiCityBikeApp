package com.example.HelsinkiCityBikeApp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "journey")
public class Journey {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(name = "departure")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime departure;

    @Column(name = "return_date")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime returnDate;

    @Column(name = "departure_station_id")
    @NotNull
    private int departureStationId;

    @Column(name = "departure_station")
    @NotEmpty(message = "Station shouldn't be empty")
    @Size(min = 2, max = 100, message = "Station should be between 2 and 100 characters")
    private String departureStation;

    @Column(name = "return_station_id")
    @NotNull
    private int returnStationId;

    @Column(name = "return_station")
    @NotEmpty(message = "Station shouldn't be empty")
    @Size(min = 2, max = 100, message = "Station should be between 2 and 100 characters")
    private String returnStation;

    @Column(name = "distance")
    @NotNull
    private double distance;

    @Column(name = "duration")
    @NotNull
    private double duration;

    public Journey() {
    }

    public Journey(LocalDateTime departure, LocalDateTime returnDate,
                   int departureStationId, String departureStation,
                   int returnStationId, String returnStation, double distance,
                   double duration) {
        this.departure = departure;
        this.returnDate = returnDate;
        this.departureStationId = departureStationId;
        this.departureStation = departureStation;
        this.returnStationId = returnStationId;
        this.returnStation = returnStation;
        this.distance = distance;
        this.duration = duration;
    }

    public String convertToKm(double distance) {
        return String.format("%.2f", distance / 1000);
    }

    public String convertToMinutes(double duration) {
        return String.format("%.2f", duration / 60);
    }

    public String format(LocalDateTime value) {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(value);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDeparture() {
        return departure;
    }

    public void setDeparture(LocalDateTime departure) {
        this.departure = departure;
    }

    public LocalDateTime getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }

    public int getDepartureStationId() {
        return departureStationId;
    }

    public void setDepartureStationId(int departureStationId) {
        this.departureStationId = departureStationId;
    }

    public String getDepartureStation() {
        return departureStation;
    }

    public void setDepartureStation(String departureStation) {
        this.departureStation = departureStation;
    }

    public int getReturnStationId() {
        return returnStationId;
    }

    public void setReturnStationId(int returnStationId) {
        this.returnStationId = returnStationId;
    }

    public String getReturnStation() {
        return returnStation;
    }

    public void setReturnStation(String returnStation) {
        this.returnStation = returnStation;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Journey{" +
                "id=" + id +
                ", departure='" + departure + '\'' +
                ", returnDate='" + returnDate + '\'' +
                ", departureStationId=" + departureStationId +
                ", departureStation='" + departureStation + '\'' +
                ", returnStationId=" + returnStationId +
                ", returnStation='" + returnStation + '\'' +
                ", distance=" + distance +
                ", duration=" + duration +
                '}';
    }
}
