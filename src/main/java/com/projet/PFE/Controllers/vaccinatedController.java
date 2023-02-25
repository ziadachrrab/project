package com.projet.PFE.Controllers;

import com.projet.PFE.Entities.centreEntity;
import com.projet.PFE.Entities.vaccinatedEntity;
import com.projet.PFE.Enumerations.villeEnumeration;
import com.projet.PFE.Services.vaccinatedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("vaccinated")
public class vaccinatedController {
    private final vaccinatedService VaccinatedService;
    @Autowired
    public vaccinatedController(vaccinatedService VaccinatedService) {
        this.VaccinatedService = VaccinatedService;
    }

    @GetMapping("/all")
    public List<vaccinatedEntity> getVaccinated(){
        return VaccinatedService.getVaccinated();
    }

    @GetMapping("/find/{cin}")
    public vaccinatedEntity getVaccinatedByCin(@PathVariable("cin") String cin){
        return VaccinatedService.findVaccinatedByCin(cin);
    }


    @PostMapping("/add")
    public void registerVaccinated(@RequestBody vaccinatedEntity Vaccinated){
        VaccinatedService.addVaccinatedEntity(Vaccinated);
    }

    @PutMapping("/update/{vaccinatedCin}")
    public void updateVaccinated(
            @PathVariable("vaccinatedCin") String cin,
            @RequestParam(required = false) String nom,
            @RequestParam(required = false) String prenom,
            @RequestParam(required = false) String n_tele,
            @RequestParam(required = false) String addresse,
            @RequestParam(required = false) String maladie,
            @RequestParam(required = false) String medicament,
            @RequestParam(required = false) villeEnumeration ville,
            @RequestParam(required = false) Date date_naissance,
            @RequestParam(required = false) centreEntity centreId) {
        VaccinatedService.updateVaccinatedEntity(cin, nom, prenom, n_tele, addresse, date_naissance, maladie, medicament, ville, centreId);
    }

    @DeleteMapping( "/delete/{vaccinatedCin}")
    public void deleteVaccinated(@PathVariable("vaccinatedCin")String cin ){
        VaccinatedService.deleteVaccinatedEntity(cin);
    }
}
