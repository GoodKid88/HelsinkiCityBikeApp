package com.example.HelsinkiCityBikeApp.services;

import com.example.HelsinkiCityBikeApp.model.Journey;
import com.example.HelsinkiCityBikeApp.repositories.JourneyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    public Page<Journey> findAll(Pageable pageable) {
        Page<Journey> journeyPageable = this.journeyRepository.findAll(pageable);
        return journeyPageable;
    }

    @Transactional
    public void save(Journey journey) {
        journeyRepository.save(journey);
    }

    public Journey findOne(int id) {
        Optional<Journey> foundPerson = journeyRepository.findById(id);
        return foundPerson.orElse(null);
    }

    @Transactional
    public void update(int id, Journey updatedJourney) {
        updatedJourney.setId(id);
        journeyRepository.save(updatedJourney);
    }

    @Transactional
    public void delete(int id) {
        journeyRepository.deleteById(id);
    }
}
