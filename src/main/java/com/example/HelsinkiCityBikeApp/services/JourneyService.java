package com.example.HelsinkiCityBikeApp.services;

import com.example.HelsinkiCityBikeApp.model.Journey;
import com.example.HelsinkiCityBikeApp.repositories.JourneyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class JourneyService {

    private final JourneyRepository journeyRepository;

    @Autowired
    public JourneyService(JourneyRepository journeyRepository) {
        this.journeyRepository = journeyRepository;
    }

    public Journey findOne(int id) {
        Optional<Journey> foundJourney = journeyRepository.findById(id);
        return foundJourney.orElse(null);
    }
    public List<Journey> findAll() {
        return journeyRepository.findAll();
    }
    @Transactional
    public void save(Journey journey) {
        journeyRepository.save(journey);
    }

    @Transactional
    public void delete(int id) {
        journeyRepository.deleteById(id);
    }

}
