package com.example.HelsinkiCityBikeApp.controllers;

import com.example.HelsinkiCityBikeApp.model.Station;
import com.example.HelsinkiCityBikeApp.services.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/stations")
public class StationController {
    private final StationService stationService;

    @Autowired
    public StationController(StationService stationService) {
        this.stationService = stationService;
    }

//    @GetMapping()
//    public String viewAllStations(Model model) {
//        model.addAttribute("stations", stationService.findAll());
//        return "/stations";
//    }

    @GetMapping("/show/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        stationService.setAdditionalInfoToStation(stationService.findOne(id));
        model.addAttribute("station", stationService.findOne(id));
        return "/show";
    }

    @GetMapping()
    public String viewHomePage(Model model) {
        return getAllStationsPageable(1, model);
    }
        @GetMapping("/{page}")
    public String getAllStationsPageable(@PathVariable(value = "page") int page, Model model){
        Page<Station> stationsPageable = this.stationService.getAllStationsForView(PageRequest.of(page - 1, 15));
        List<Station> stations = stationsPageable.getContent();
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", stationsPageable.getTotalPages());
        model.addAttribute("totalItems", stationsPageable.getTotalElements());
        model.addAttribute("stations", stations);
        model.addAttribute("module", "stations");
        return "stations";
    }
}
