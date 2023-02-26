package com.projet.pfe.services;

import com.projet.pfe.entities.Facility;
import com.projet.pfe.repositories.FacilityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FacilityService {
    private final FacilityRepository facilityRepository;

    public FacilityService(FacilityRepository facilityRepository) {
        this.facilityRepository = facilityRepository;
    }

    public List<Facility> findAll() {
        return facilityRepository.findAll();
    }

    public Facility find(String name) {
        return facilityRepository.findByName(name)
                .orElseThrow(() -> new IllegalStateException("Facility not found"));
    }

    public void insert(Facility facility) {
        facilityRepository.findById(facility.getId())
                .ifPresentOrElse($ -> {
                    throw new IllegalStateException("Facility already exists");
                }, () -> facilityRepository.save(facility));
    }

    public void deleteByName(String nom) {
        facilityRepository.findByName(nom)
                .ifPresentOrElse(facilityRepository::delete, () -> {
                    throw new IllegalStateException("Centre with name " + nom + " does not exist");
                });
    }

    public void delete(Facility facility) {
        facilityRepository.delete(facility);
    }

    public void update(Facility facility) {
        facilityRepository.save(facility);
    }

    @Transactional
    public Facility update(String name, Facility request) {
        final Facility result = find(name);

        if (result != null) {
            facilityRepository.findByName(request.getName())
                    .ifPresentOrElse($ -> {
                        throw new IllegalStateException("Facility with the name " + request.getName() + " already exists");
                    }, () -> result.setName(request.getName()));
            result.setCapacity(request.getCapacity());
            result.setAddress(request.getAddress());
            result.setSupervisor(request.getSupervisor());
            facilityRepository.save(request);
        }
        return result;
    }

}

