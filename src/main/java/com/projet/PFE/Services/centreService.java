package com.projet.PFE.Services;

import com.projet.PFE.Entities.centreEntity;
import com.projet.PFE.Repositories.centreRepository;
import com.projet.PFE.Repositories.responsableRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class centreService {
    private final centreRepository CentreRepository;
    private final responsableRepository ResponsableRepository;
    public centreService(centreRepository centreRepository, responsableRepository responsableRepository) {
        CentreRepository = centreRepository;
        ResponsableRepository = responsableRepository;
    }

    public List<centreEntity> getCentre(){
        return CentreRepository.findAll();
    }

    public centreEntity findCentreByName(String nom){
        return CentreRepository.findByName(nom).orElseThrow(() -> new IllegalStateException("Centre not found"));
    }

        public void addCentreEntity(centreEntity CentreEntity){
            Optional<centreEntity> centreEntityOptional = CentreRepository.findByName(CentreEntity.getNom());
            if(centreEntityOptional.isPresent()){
                throw new IllegalStateException("Centre already exists");
            }
//            Optional<responsableEntity> respo = ResponsableRepository.findById((CentreEntity.getResponsableId()));
//            if(!respo.isPresent()){
//                throw new IllegalStateException("respo doesnt exist");
//            }
//            centreEntity centreEntityStored = new centreEntity();
//            centreEntityStored.setNom(CentreEntity.getNom());
//            centreEntityStored.setCapacitee(CentreEntity.getCapacitee());
//            centreEntityStored.setVille(CentreEntity.getVille());
//            centreEntityStored.setAddresse(CentreEntity.getAddresse());
//            centreEntityStored.setResponsableId(respo.get());
            CentreRepository.save(CentreEntity);

        }

    public void deleteCentreEntity(String nom){
        boolean exists = CentreRepository.findByName(nom).isPresent();
        if(!exists){
            throw new IllegalStateException("Centre with name " +nom+ " does not exist");
        }
        CentreRepository.deleteByName(nom);
    }

    @Transactional
    public void updateCentreEntity(String nom, centreEntity request) {
        centreEntity centre = CentreRepository.findByName(nom).orElseThrow(() -> new IllegalStateException("Centre with name " + nom + " does not exist"));
        if(request.getNom() != null && request.getNom().length() > 0 && !Objects.equals(centre.getNom(), request.getNom())) {
            Optional<centreEntity> centreEntityOptional = CentreRepository.findByName(request.getNom());
            if(centreEntityOptional.isPresent()) {
                throw new IllegalStateException("Centre with the name " + request.getNom() + " already exists");
            }
            centre.setNom(request.getNom());
        }
        if(request.getAddresse() != null && request.getAddresse().length() > 0 && !Objects.equals(centre.getAddresse(), request.getAddresse())) {
            centre.setAddresse(request.getAddresse());
        }
        if(request.getCapacitee() != 0 && request.getCapacitee() > 0 && !Objects.equals(centre.getCapacitee(), request.getCapacitee())) {
            centre.setCapacitee(request.getCapacitee());
        }
        if(request.getResponsableId() != null && !Objects.equals(centre.getResponsableId(), request.getResponsableId())) {
            centre.setResponsableId(request.getResponsableId());
        }
    }

}

