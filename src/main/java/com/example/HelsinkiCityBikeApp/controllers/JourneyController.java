package com.example.HelsinkiCityBikeApp.controllers;

import com.example.HelsinkiCityBikeApp.model.Journey;
import com.example.HelsinkiCityBikeApp.services.JourneyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/journeys")
public class JourneyController {
    private final JourneyService journeyService;

    @Autowired
    public JourneyController(JourneyService journeyService) {
        this.journeyService = journeyService;
    }

    @GetMapping("/")
    public String homePage() {
        return "/index";
    }

    @GetMapping()
    public String viewAllJourneys(Model model) {
        model.addAttribute("journeys", journeyService.findAll());
        return "/journeys";
    }

    @GetMapping("/new")
    public String newJourney(@ModelAttribute("journey") Journey journey) {
        return "/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("journey") @Valid Journey journey,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "/new";
        journeyService.save(journey);
        return "redirect:/journeys";
    }
}
