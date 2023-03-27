package com.example.HelsinkiCityBikeApp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "station")
public class Station {
    @Id
    @Column(name = "fid")
    private int fid;

    @Column(name = "station_id")
    private int stationId;
    @Column(name = "station_name_fi")
    private String stationNameFI;
    @Column(name = "station_name_sw")
    private String stationNameSW;

    @Column(name = "station_name_en")
    private String stationNameEn;
    @Column(name = "address_fi")
    private String addressFI;
    @Column(name = "address_sw")
    private String addressSW;
    @Column(name = "city_fi")
    private String cityFI;
    @Column(name = "city_sw")
    private String citySW;
    @Column(name = "operator")
    private String operator;
    @Column(name = "capacity")
    private int capacity;
    @Column(name = "coordinate_x")
    private double x;
    @Column(name = "coordinate_y")
    private double y;

    private int startingFromStation;
    private int endingFromStation;
    private double avgDistanceFromStation;
    private double avgDistanceToStation;

    private String top5returnStations;
    private String top5startStations;

    public String getTop5returnStations() {
        return top5returnStations;
    }

    public void setTop5returnStations(String top5returnStations) {
        this.top5returnStations = top5returnStations;
    }

    public String getTop5startStations() {
        return top5startStations;
    }

    public void setTop5startStations(String top5startStations) {
        this.top5startStations = top5startStations;
    }

    public Station() {
    }

    public Station(int fid, int stationId, String stationNameFI,
                   String stationNameSW, String stationNameEn, String addressFI,
                   String addressSW, String cityFI, String citySW, String operator,
                   int capacity, double x, double y) {
        this.fid = fid;
        this.stationId = stationId;
        this.stationNameFI = stationNameFI;
        this.stationNameSW = stationNameSW;
        this.stationNameEn = stationNameEn;
        this.addressFI = addressFI;
        this.addressSW = addressSW;
        this.cityFI = cityFI;
        this.citySW = citySW;
        this.operator = operator;
        this.capacity = capacity;
        this.x = x;
        this.y = y;
    }

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    public String getStationNameFI() {
        return stationNameFI;
    }

    public void setStationNameFI(String stationNameFI) {
        this.stationNameFI = stationNameFI;
    }

    public String getStationNameSW() {
        return stationNameSW;
    }

    public void setStationNameSW(String stationNameSW) {
        this.stationNameSW = stationNameSW;
    }

    public String getStationNameEn() {
        return stationNameEn;
    }

    public void setStationNameEn(String stationNameEn) {
        this.stationNameEn = stationNameEn;
    }

    public String getAddressFI() {
        return addressFI;
    }

    public void setAddressFI(String addressFI) {
        this.addressFI = addressFI;
    }

    public String getAddressSW() {
        return addressSW;
    }

    public void setAddressSW(String addressSW) {
        this.addressSW = addressSW;
    }

    public String getCityFI() {
        return cityFI;
    }

    public void setCityFI(String cityFI) {
        this.cityFI = cityFI;
    }

    public String getCitySW() {
        return citySW;
    }

    public void setCitySW(String citySW) {
        this.citySW = citySW;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int getStartingFromStation() {
        return startingFromStation;
    }

    public void setStartingFromStation(int startingFromStation) {
        this.startingFromStation = startingFromStation;
    }

    public int getEndingFromStation() {
        return endingFromStation;
    }

    public void setEndingFromStation(int endingFromStation) {
        this.endingFromStation = endingFromStation;
    }

    public double getAvgDistanceFromStation() {
        return avgDistanceFromStation;
    }

    public void setAvgDistanceFromStation(double avgDistanceFromStation) {
        this.avgDistanceFromStation = avgDistanceFromStation;
    }

    public double getAvgDistanceToStation() {
        return avgDistanceToStation;
    }

    public void setAvgDistanceToStation(double avgDistanceToStation) {
        this.avgDistanceToStation = avgDistanceToStation;
    }
}