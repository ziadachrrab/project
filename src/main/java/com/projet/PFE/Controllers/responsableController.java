package com.projet.PFE.Controllers;

import com.projet.PFE.Entities.centreEntity;
import com.projet.PFE.Entities.responsableEntity;
import com.projet.PFE.Services.responsableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("/responsable")

public class responsableController {


        private final responsableService ResponsableService;
        @Autowired
        public responsableController(responsableService ResponsableService) {this.ResponsableService = ResponsableService;}

        @GetMapping("/all")
        public List<responsableEntity> getResponsable(){
            return ResponsableService.getResponsable();
        }

        @GetMapping("/find/{cin}")
        public responsableEntity getResponsableByCin(@PathVariable("cin") String cin){
            return ResponsableService.findResponsableByCin(cin);
        }
        @GetMapping("/activeResponsables")
        public List<responsableEntity> getActiveUsers() {
            List<responsableEntity> activeResponsables = new ArrayList<>();
            for (responsableEntity responsable : ResponsableService.getResponsable()) {
                if (!responsable.isActive()) {
                    activeResponsables.add(responsable);
                }
            }
            return activeResponsables;
        }

        @PostMapping("/add")
        public void registerResponsable(@RequestBody responsableEntity Responsable){
            ResponsableService.addResponsableEntity(Responsable);
        }

        @PutMapping("/update/{responsableCin}")
        public void updateResponsable(
                @PathVariable("responsableCin") String cin,
                @RequestParam(required = false) String nom,
                @RequestParam(required = false) String prenom,
                @RequestParam(required = false) String email,
                @RequestParam(required = false) centreEntity centreId) {
            ResponsableService.updateResponsableEntity( cin, nom, prenom, email, centreId);
        }

        @DeleteMapping("/delete/{responsableCin}")
        public void deleteRessponsable(@PathVariable("responsableCin")String cin ){
            ResponsableService.deleteResponsableEntity(cin);
        }
}
