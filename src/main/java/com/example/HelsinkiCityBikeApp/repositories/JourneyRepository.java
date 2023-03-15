package com.example.HelsinkiCityBikeApp.repositories;

import com.example.HelsinkiCityBikeApp.model.Journey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JourneyRepository extends JpaRepository<Journey, Integer> {
}