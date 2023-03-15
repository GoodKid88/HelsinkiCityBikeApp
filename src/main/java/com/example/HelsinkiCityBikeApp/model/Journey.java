package com.example.HelsinkiCityBikeApp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "journey")
public class Journey {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "departure_station")
    @NotEmpty(message = "Station shouldn't be empty")
    @Size(min = 2, max = 30, message = "Station should be between 2 and 30 characters")
    private String departureStation;

    @Column(name = "return_station")
    @NotEmpty(message = "Station shouldn't be empty")
    @Size(min = 2, max = 30, message = "Station should be between 2 and 30 characters")
    private String returnStation;

    @Column(name = "distance")
    @NotNull
    private double distance;

    @Column(name = "duration")
    @NotNull
    private double duration;

    public Journey() {
    }

    public Journey(String departureStation, String returnStation, double distance, double duration) {
        this.departureStation = departureStation;
        this.returnStation = returnStation;
        this.distance = distance;
        this.duration = duration;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepartureStation() {
        return departureStation;
    }

    public void setDepartureStation(String departureStation) {
        this.departureStation = departureStation;
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
}
