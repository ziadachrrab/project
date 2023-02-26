package com.projet.pfe.services;

import com.projet.pfe.entities.Facility;
import com.projet.pfe.entities.Supervisor;
import com.projet.pfe.repositories.SupervisorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class SupervisorService {
    private final SupervisorRepository supervisorRepository;

    public SupervisorService(SupervisorRepository supervisorRepository) {
        this.supervisorRepository = supervisorRepository;
    }

    public List<Supervisor> findAll(){
        return supervisorRepository.findAll();
    }

    public Optional<Supervisor> find(long id) {
        return supervisorRepository.findById(id);
    }

    public Optional<Supervisor> findSupervisorByCin(String cin){
        return supervisorRepository.findAll()
                .stream()
                .filter(supervisor -> supervisor.getAppUserDetails().getCin().equals(cin))
                .findAny();
    }

    public void insert(Supervisor supervisor){
        supervisorRepository.findById(supervisor.getId())
                .ifPresentOrElse($ -> {
                    throw new IllegalStateException("Supervisor already exists");
                }, () -> supervisorRepository.save(supervisor));
    }

    public void deleteByCin(String cin){
        findSupervisorByCin(cin)
                .ifPresentOrElse(supervisorRepository::delete, () -> {
                    throw new IllegalStateException("Supervisor with CIN " + cin + " does not exist");
                });
    }

    public void delete(Supervisor supervisor) {
        supervisorRepository.delete(supervisor);
    }

    public void update(Supervisor supervisor) {
        supervisorRepository.save(supervisor);
    }

    @Transactional
    public Supervisor update(String cin,
                       String firstName,
                       String lastName,
                       String email,
                       Facility facility) {

        final Supervisor supervisor = findSupervisorByCin(cin).orElseThrow(() -> {
            throw new IllegalStateException("Supervisor was not found");
        });

        if (supervisor != null) {
            if (firstName != null && !firstName.isBlank())
                supervisor.getAppUserDetails().setFirstName(firstName);
            if (lastName != null && !lastName.isBlank())
                supervisor.getAppUserDetails().setLastName(lastName);

            if(email != null && !email.isBlank() && !Objects.equals(supervisor.getAppUserDetails().getEmail(), email)) {
                findSupervisorByCin(cin).ifPresentOrElse($ -> {
                    throw new IllegalStateException("Email taken");
                }, () -> supervisor.getAppUserDetails().setEmail(email));
            }
            if (facility != null)
                supervisor.setFacility(facility);
            supervisorRepository.save(supervisor);
        }
       return supervisor;
    }
}
