package com.projet.pfe.controllers;

import com.projet.pfe.entities.Facility;
import com.projet.pfe.entities.Supervisor;
import com.projet.pfe.services.SupervisorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/supervisor")

public class SupervisorController {

    private final SupervisorService supervisorService;

    public SupervisorController(SupervisorService supervisorService) {
        this.supervisorService = supervisorService;
    }

    @GetMapping("/all")
    public List<Supervisor> supervisors() {
        return supervisorService.findAll();
    }

    @GetMapping("/find/{cin}")
    public Supervisor byCin(@PathVariable("cin") String cin) {
        return supervisorService.findSupervisorByCin(cin).orElseThrow(() -> {
            throw new IllegalStateException("Supervisor with the CIN " + cin + " was not found!");
        });
    }

    @GetMapping("/active")
    public List<Supervisor> activeSupervisors() {
        return supervisorService.findAll()
                .stream()
                .filter(Supervisor::isActive).toList();
    }

    @PostMapping("/add")
    public void registerSupervisor(@RequestBody Supervisor supervisor) {
        supervisorService.insert(supervisor);
    }

    @PutMapping("/update/{cin}")
    public void updateSupervisor(
            @PathVariable("cin") String cin,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) Facility facility) {
        supervisorService.update(cin, firstName, lastName, email, facility);
    }

    @DeleteMapping("/delete/{cin}")
    public void deleteSupervisor(@PathVariable("cin") String cin) {
        supervisorService.deleteByCin(cin);
    }
}
