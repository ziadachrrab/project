package com.projet.PFE.Services;

import com.projet.PFE.Entities.centreEntity;
import com.projet.PFE.Entities.vaccinatedEntity;
import com.projet.PFE.Enumerations.villeEnumeration;
import com.projet.PFE.Repositories.vaccinatedRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class vaccinatedService {
    private final vaccinatedRepository VaccinatedRepository;

    public vaccinatedService(vaccinatedRepository VaccinatedRepository) {
        this.VaccinatedRepository = VaccinatedRepository;
    }

    public List<vaccinatedEntity> getVaccinated(){
        return VaccinatedRepository.findAll();
    }

    public vaccinatedEntity findVaccinatedByCin(String cin){
        return VaccinatedRepository.findByCin(cin).orElseThrow(() -> new IllegalStateException("Vaccinated not found"));
    }

    public void addVaccinatedEntity(vaccinatedEntity VaccinatedEntity){
        Optional<vaccinatedEntity> vaccinatedEntityOptional = VaccinatedRepository.findByCin(VaccinatedEntity.getCin());
        if(vaccinatedEntityOptional.isPresent()){
            throw new IllegalStateException("Already vaccinated");
        }
        VaccinatedRepository.save(VaccinatedEntity);
    }

    public void deleteVaccinatedEntity(String cin){
        boolean exists =VaccinatedRepository.findByCin(cin).isPresent();
        if(!exists){
            throw new IllegalStateException("Vaccinated with CIN "+cin+" does not exist");
        }
        VaccinatedRepository.deleteByCin(cin);
    }

    @Transactional
    public void updateVaccinatedEntity(String cin, String nom, String prenom, String n_tele, String addresse,
                                       Date date_naissance, String maladie, String medicament, villeEnumeration ville, centreEntity centreId) {
        vaccinatedEntity vaccinated = VaccinatedRepository.findByCin(cin).orElseThrow(() -> new IllegalStateException("Student with CIN " + cin + " does not exist"));
        if (nom != null && nom.length() > 0 && !Objects.equals(vaccinated.getNom(), nom)) {
            vaccinated.setNom(nom);
        }
        if (prenom != null && prenom.length() > 0 && !Objects.equals(vaccinated.getPrenom(), prenom)) {
            vaccinated.setPrenom(prenom);
        }
        if (n_tele != null && n_tele.length() > 0 && n_tele.length() < 13 && !Objects.equals(vaccinated.getN_tele(), n_tele)) {
            vaccinated.setN_tele(n_tele);
        }
        if (addresse != null && addresse.length() > 0 && !Objects.equals(vaccinated.getAddresse(), addresse)) {
            vaccinated.setAddresse(addresse);
        }
        if (date_naissance != null && !Objects.equals(vaccinated.getDate_naissance(), date_naissance)) {
            vaccinated.setDate_naissance(date_naissance);
        }
        if (maladie != null && !Objects.equals(vaccinated.getMaladie(), maladie)) {
            vaccinated.setMaladie(maladie);
        }
        if (medicament != null && !Objects.equals(vaccinated.getMedicament(), medicament)) {
            vaccinated.setMedicament(medicament);
        }
        if(ville != null && !Objects.equals(vaccinated.getVille(), ville)) {
            vaccinated.setVille(ville);
        }
        if (centreId != null && !centreId.toString().isEmpty() && !Objects.equals(vaccinated.getCentreId(), centreId)) {
            vaccinated.setCentreId(centreId);
        }


    }
}
