package com.example.HelsinkiCityBikeApp.repositories;

import com.example.HelsinkiCityBikeApp.model.Journey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JourneyRepository extends JpaRepository<Journey, Integer> {
int countAllByDepartureStation(String station);
int countAllByReturnStation(String station);
    @Query(value = "select sum(b.distance) from Journey b where b.departureStation=:station")
    int countAllDistanceFromStation(String station);

    @Query(value = "select sum(b.distance) from Journey b where b.returnStation=:station")
    int countAllDistanceToStation(String station);

    @Query(value = "select (b.departureStation) from Journey b where b.returnStation=:station group by b.departureStation order by count(*) limit 5")
    List<String> findByReturnStationOrderByCountLimit5(String station);

    @Query(value = "select (b.returnStation) from Journey b where b.departureStation=:station group by b.returnStation order by count(*) limit 5")
    List<String> findByDepartureStationOrderByCountLimit5(String station);
}
