package com.projet.pfe.controllers;

import com.projet.pfe.entities.Facility;
import com.projet.pfe.entities.VaccinatedUser;
import com.projet.pfe.enumerations.City;
import com.projet.pfe.services.VaccinatedService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("vaccinated")
public class VaccinatedController {

    private final VaccinatedService vaccinatedService;

    public VaccinatedController(VaccinatedService vaccinatedService) {
        this.vaccinatedService = vaccinatedService;
    }

    @GetMapping("/all")
    public List<VaccinatedUser> vaccinatedUsers() {
        return vaccinatedService.findAll();
    }

    @GetMapping("/find/{cin}")
    public VaccinatedUser byCin(@PathVariable("cin") String cin) {
        return vaccinatedService.findVaccinatedByCin(cin).orElseThrow(() -> {
            throw new IllegalStateException("Vaccinated user with CIN " + cin + " was not found");
        });
    }


    @PostMapping("/add")
    public void registerVaccinated(@RequestBody VaccinatedUser vaccinatedUser) {
        vaccinatedService.insert(vaccinatedUser);
    }

    @PutMapping("/update/{cin}")
    public void updateVaccinated(
            @PathVariable("cin") String cin,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String phoneNumber,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) String illness,
            @RequestParam(required = false) String medicine,
            @RequestParam(required = false) Boolean pregnant,
            @RequestParam(required = false) String gender,
            @RequestParam(required = false) LocalDate birthDate,
            @RequestParam(required = false) City city,
            @RequestParam(required = false) Facility facility) {
        vaccinatedService.update(cin,
                firstName,
                lastName,
                phoneNumber,
                address, illness, medicine, pregnant, gender, birthDate, city, facility);
    }

    @DeleteMapping("/delete/{cin}")
    public void deleteVaccinated(@PathVariable("cin") String cin) {
        vaccinatedService.deleteByCin(cin);
    }
}
