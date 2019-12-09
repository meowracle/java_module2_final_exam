package com.codegym.controllers;

import com.codegym.models.City;
import com.codegym.models.Country;
import com.codegym.services.CityService;
import com.codegym.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class CityController {
    @Autowired
    private CityService cityService;

    @Autowired
    private CountryService countryService;

    @ModelAttribute("countries")
    public Page<Country> countries(Pageable pageable){
        return countryService.findAll(pageable);
    }

    @GetMapping("/city/cities")
    public ModelAndView listCities(@RequestParam("key_word") Optional<String> key_word, @PageableDefault(size = 5, sort = "name") Pageable pageable) {
        Page<City> cities;
        if (key_word.isPresent()) {
            cities = cityService.findAllByNameContaining(key_word.get(), pageable);
        } else {
            cities = cityService.findAll(pageable);
        }

        ModelAndView modelAndView = new ModelAndView("/city/list");
        modelAndView.addObject("cities", cities);
        return modelAndView;
    }

    @GetMapping("/city/create-city")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("/city/create");
        modelAndView.addObject("city", new City());
        return modelAndView;
    }

    @PostMapping("/city/create-city")
    public ModelAndView saveCity(@Valid @ModelAttribute("city") City city, BindingResult bindingResult){
        if (bindingResult.hasFieldErrors()){
            ModelAndView modelAndView = new ModelAndView("/city/create");
            return modelAndView;
        } else {
            cityService.save(city);
            ModelAndView modelAndView = new ModelAndView("city/create");
            modelAndView.addObject("city", new City());
            modelAndView.addObject("message", "Added new city");
            return modelAndView;
        }
    }

    @GetMapping("/city/edit-city/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        City city = cityService.findById(id);
        if (city != null) {
            ModelAndView modelAndView = new ModelAndView("/city/edit");
            modelAndView.addObject("city", city);
            return modelAndView;

        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/city/edit-city")
    public ModelAndView updateBook(@Valid @ModelAttribute("city") City city, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("/city/edit");
            return modelAndView;
        }else {
            cityService.save(city);
            ModelAndView modelAndView = new ModelAndView("/city/edit");
            modelAndView.addObject("city", city);
            modelAndView.addObject("message", "Edited city's information");
            return modelAndView;
        }
    }

    @GetMapping("/city/delete-city/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id) {
        City city = cityService.findById(id);
        if (city != null) {
            ModelAndView modelAndView = new ModelAndView("/city/delete");
            modelAndView.addObject("city", city);
            return modelAndView;

        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/city/delete-city")
    public String deleteCustomer(@ModelAttribute("city") City city) {
        cityService.delete(city.getId());
        return "redirect:cities";
    }

    @GetMapping("city/view/{id}")
    public String getCityDetail(@PathVariable("id") Long id, Model model) {
        City city = cityService.findById(id);
        if (city != null) {
            model.addAttribute("city", city);
            return "city/view";
        } else {
            return "error.404";
        }
    }
}
