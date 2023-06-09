package com.example.HelsinkiCityBikeApp.repositories;

import com.example.HelsinkiCityBikeApp.model.Station;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StationRepository extends JpaRepository<Station, Integer> {
    Page<Station> findAll(Pageable pageable);
}
