package com.projet.PFE.Controllers;

import com.projet.PFE.Entities.centreEntity;
import com.projet.PFE.Services.centreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/centre")
public class centreController {
        private final centreService CentreService;

        @Autowired
        public centreController(centreService CentreService) {
            this.CentreService = CentreService;
        }

        @GetMapping("/all")
        public List<centreEntity> getCentre(){
            return CentreService.getCentre();
        }

        @GetMapping("/find/{nom}")
        public centreEntity getCentreByName(@PathVariable("nom") String nom){
            return CentreService.findCentreByName(nom);
        }

        @PostMapping("/add")
        public void registerCentre(@RequestBody centreEntity Centre){
            CentreService.addCentreEntity(Centre);
        }

        @PutMapping("/update/{centreName}")
        public void updateCentre(@PathVariable("centreName") String nom, @RequestBody centreEntity request) {
            CentreService.updateCentreEntity(nom, request);
        }

        @DeleteMapping("/delete/{centreName}")
            public void deleteCentre(@PathVariable("centreName")String nom ){
                CentreService.deleteCentreEntity(nom);
            }


}
