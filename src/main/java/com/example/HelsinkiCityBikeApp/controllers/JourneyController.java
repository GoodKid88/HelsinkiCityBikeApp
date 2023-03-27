package com.example.HelsinkiCityBikeApp.controllers;

import com.example.HelsinkiCityBikeApp.model.Journey;
import com.example.HelsinkiCityBikeApp.services.JourneyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/journeys")
public class JourneyController {
    private final JourneyService journeyService;

    @Autowired
    public JourneyController(JourneyService journeyService)  {
        this.journeyService = journeyService;
    }

//    @GetMapping()
//    public String viewAllJourneys(Model model) {
//        model.addAttribute("journeys", journeyService.findAll());
//        return "/journeys";
//    }

    @GetMapping()
    public String viewHomePage(Model model) {
        return getAllJourneysPageable(1, model);
    }

    @GetMapping("/{page}")
    public String getAllJourneysPageable(@PathVariable(value = "page") int page, Model model){
        Page<Journey> journeysPageable = this.journeyService.getAllJourneysForView(PageRequest.of(page - 1, 15));
        List<Journey> journeys = journeysPageable.getContent();
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", journeysPageable.getTotalPages());
        model.addAttribute("totalItems", journeysPageable.getTotalElements());
        model.addAttribute("journeys", journeys);
        model.addAttribute("module", "journeys");
        return "journeys";
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
