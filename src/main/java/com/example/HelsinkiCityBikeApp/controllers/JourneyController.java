package com.example.HelsinkiCityBikeApp.controllers;

import com.example.HelsinkiCityBikeApp.model.Journey;
import com.example.HelsinkiCityBikeApp.services.JourneyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/journeys")
public class JourneyController {

    private final JourneyService journeyService;

    @Autowired
    public JourneyController(JourneyService journeyService) {
        this.journeyService = journeyService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("journeys", journeyService.findAll());
        return "journeys/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("journey", journeyService.findOne(id));
        return "journeys/show";
    }

    @GetMapping("/new")
    public String newJourney(@ModelAttribute("journey") Journey journey) {
        return "journeys/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("journey") @Valid Journey journey,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "journeys/new";
        journeyService.save(journey);
        return "redirect:/journeys";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        journeyService.delete(id);
        return "redirect:/journeys";
    }
}
