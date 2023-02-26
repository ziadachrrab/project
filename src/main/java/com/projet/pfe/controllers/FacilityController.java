package com.projet.pfe.controllers;

import com.projet.pfe.entities.Facility;
import com.projet.pfe.services.FacilityService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/facility")
public class FacilityController {
    private final FacilityService facilityService;

    public FacilityController(FacilityService facilityService) {
        this.facilityService = facilityService;
    }

    @GetMapping("/all")
    public List<Facility> facilities() {
        return facilityService.findAll();
    }

    @GetMapping("/find/{name}")
    public Facility byName(@PathVariable("name") String name) {
        return facilityService.find(name);
    }

    @PostMapping("/add")
    public void registerFacility(@RequestBody Facility facility) {
        facilityService.insert(facility);
    }

    @PutMapping("/update/{name}")
    public void updateFacility(@PathVariable("name") String name, @RequestBody Facility request) {
        facilityService.update(name, request);
    }

    @DeleteMapping("/delete/{name}")
    public void deleteFacility(@PathVariable("name") String name) {
        facilityService.deleteByName(name);
    }

}
