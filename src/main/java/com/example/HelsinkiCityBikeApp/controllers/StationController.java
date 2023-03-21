package com.example.HelsinkiCityBikeApp.controllers;

import com.example.HelsinkiCityBikeApp.services.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("stations")
public class StationController {

    private final StationService stationService;

    @Autowired
    public StationController(StationService stationService) {
        this.stationService = stationService;
    }

    @GetMapping()
    public String viewAllStations(Model model) {
        model.addAttribute("stations", stationService.findAll());
        return "/stations";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("journey", stationService.findOne(id));
        return "/show";
    }

//    @PostMapping("/uploadStations")
//    public String uploadStationsFromCSV(){
//        stationService.uploadStations("https://opendata.arcgis.com/datasets/726277c507ef4914b0aec3cbcfcbfafc_0.csv");
//        return "Upload successful";
//    }
}
