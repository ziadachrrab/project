package com.projet.PFE.Services;

import com.projet.PFE.Entities.centreEntity;
import com.projet.PFE.Entities.responsableEntity;
import com.projet.PFE.Repositories.responsableRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class responsableService {
    private final responsableRepository RespnsableRepository;

    public responsableService(responsableRepository RespnsableRepository) {
        this.RespnsableRepository = RespnsableRepository;
    }

    public List<responsableEntity> getResponsable(){
        return RespnsableRepository.findAll();
    }

    public responsableEntity findResponsableByCin(String cin){
        return RespnsableRepository.findByCin(cin).orElseThrow(() -> new IllegalStateException("Responsable not found"));
    }

    public void addResponsableEntity(responsableEntity ResponsableEntity){
        Optional<responsableEntity> responsableEntityOptional = RespnsableRepository.findByCin(ResponsableEntity.getCin());
        if(responsableEntityOptional.isPresent()){
            throw new IllegalStateException("Responsable already exists");
        }
        RespnsableRepository.save(ResponsableEntity);
    }

    public void deleteResponsableEntity(String cin){
        boolean exists = RespnsableRepository.findByCin(cin).isPresent();
        if(!exists){
            throw new IllegalStateException("Responsable with CIN "+cin+" does not exist");
        }
        RespnsableRepository.deleteByCin(cin);
    }

    @Transactional
    public void updateResponsableEntity(String cin, String nom, String prenom, String email, centreEntity cenreId) {
        responsableEntity responsable = RespnsableRepository.findByCin(cin).orElseThrow(() -> new IllegalStateException("Rersponsable with CIN "+ cin + " does not exist"));
        if(nom != null && nom.length() > 0 && !Objects.equals(responsable.getNom(), nom)){
            responsable.setNom(nom);
        }
        if(prenom != null && prenom.length() > 0 && !Objects.equals(responsable.getPrenom(), prenom)) {
            responsable.setPrenom(prenom);
        }
        if(email != null && email.length() > 0 && !Objects.equals(responsable.getEmail(), email)) {
            Optional<responsableEntity> responsableEntityOptional = RespnsableRepository.findByCin(cin);
            if(responsableEntityOptional.isPresent()){
                throw new IllegalStateException("Email taken");
            }
            responsable.setEmail(email);
        }
        if(cenreId != null && !Objects.equals(responsable.getCentreId(), cenreId)) {
            responsable.setCentreId(cenreId);
        }
    }
}
