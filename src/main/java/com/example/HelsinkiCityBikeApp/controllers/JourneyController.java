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
    public JourneyController(JourneyService journeyService) {
        this.journeyService = journeyService;
    }

    @GetMapping()
    public String viewHomePage(Model model) {
        return getAll(1, model);
    }

    @GetMapping("/{page}")
    public String getAll(@PathVariable(value = "page") int page, Model model){
        Page<Journey> journeysPageable = this.journeyService.findAll(PageRequest.of(page - 1, 15));
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

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("journey", journeyService.findOne(id));
        return "/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("journey") @Valid Journey journey, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "/edit";
        journeyService.delete(id);
        journeyService.update(id, journey);
        return "redirect:/journeys";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        journeyService.delete(id);
        return "redirect:/journeys";
    }
}
