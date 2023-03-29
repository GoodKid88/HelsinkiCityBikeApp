package com.example.HelsinkiCityBikeApp.controllers;

import com.example.HelsinkiCityBikeApp.services.CSVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/index")
public class CSVController {

    private final CSVService csvService;

    @Autowired
    public CSVController(CSVService csvService) {
        this.csvService = csvService;
    }

    @GetMapping()
    public String homePage() {
        return "/index";
    }

    @GetMapping("/upload")
    public String uploadJourneys() {
        csvService.upload();
        return "redirect:/index";
    }
}