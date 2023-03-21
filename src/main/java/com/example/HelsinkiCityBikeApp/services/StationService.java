package com.example.HelsinkiCityBikeApp.services;

import com.example.HelsinkiCityBikeApp.model.Station;
import com.example.HelsinkiCityBikeApp.repositories.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class StationService {

    private final StationRepository stationRepository;

    @Autowired
    public StationService(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    public Station findOne(int id) {
        Optional<Station> foundStation = stationRepository.findById(id);
        return foundStation.orElse(null);
    }

    public List<Station> findAll() {
        return stationRepository.findAll(PageRequest.of(0, 30)).getContent();
    }
}
