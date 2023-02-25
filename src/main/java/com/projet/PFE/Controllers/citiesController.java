package com.projet.PFE.Controllers;

import com.projet.PFE.Enumerations.villeEnumeration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cities")
public class citiesController {
    @GetMapping("/morocco")
    public List<String> getMoroccanCities() {
        return Arrays.asList(villeEnumeration.values()).stream().map(villeEnumeration::toString).collect(Collectors.toList());
    }
}
