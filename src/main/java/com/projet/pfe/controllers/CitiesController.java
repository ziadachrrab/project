package com.projet.pfe.controllers;

import com.projet.pfe.enumerations.City;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/cities")
public class CitiesController {
    @GetMapping("/morocco")
    public List<String> getMoroccanCities() {
        return Arrays.stream(City.values())
                .map(city -> StringUtils.capitalize(city.name()))
                .toList();
    }
}
