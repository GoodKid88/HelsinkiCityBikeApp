package com.example.HelsinkiCityBikeApp.services;

import com.example.HelsinkiCityBikeApp.model.Station;
import com.example.HelsinkiCityBikeApp.repositories.JourneyRepository;
import com.example.HelsinkiCityBikeApp.repositories.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class StationService {
    private final StationRepository stationRepository;
    private final JourneyRepository journeyRepository;

    @Autowired
    public StationService(StationRepository stationRepository, JourneyRepository journeyRepository) {
        this.stationRepository = stationRepository;
        this.journeyRepository = journeyRepository;
    }

    public void setAdditionalInfoToStation(Station station) {
        station.setStartingFromStation(journeyRepository.countAllByDepartureStation(station.getStationNameFI()));
        station.setEndingFromStation(journeyRepository.countAllByReturnStation(station.getStationNameFI()));
        station.setTop5returnStations(journeyRepository.findByReturnStationOrderByCountLimit5(station.getStationNameFI()).toString());
        station.setTop5startStations(journeyRepository.findByDepartureStationOrderByCountLimit5(station.getStationNameFI()).toString());
        station.setAvgDistanceFromStation(journeyRepository.countAllDistanceFromStation(station.getStationNameFI()) / station.getStartingFromStation() * 0.001);
        station.setAvgDistanceToStation(journeyRepository.countAllDistanceToStation(station.getStationNameFI()) / station.getEndingFromStation() * 0.001);
    }

    public Station findOne(int id) {
        Optional<Station> foundStation = stationRepository.findById(id);
        return foundStation.orElse(null);
    }

    public Page<Station> getAllStationsForView(Pageable pageable) {
        return this.stationRepository.findAll(pageable);
    }
}
