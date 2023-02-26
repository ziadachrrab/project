package com.projet.pfe.services;

import com.projet.pfe.entities.Facility;
import com.projet.pfe.entities.VaccinatedUser;
import com.projet.pfe.enumerations.City;
import com.projet.pfe.repositories.VaccinatedRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class VaccinatedService {
    private final VaccinatedRepository vaccinatedRepository;

    public VaccinatedService(VaccinatedRepository vaccinatedRepository) {
        this.vaccinatedRepository = vaccinatedRepository;
    }

    public List<VaccinatedUser> findAll() {
        return vaccinatedRepository.findAll();
    }
    
    public Optional<VaccinatedUser> find(long id) {
        return vaccinatedRepository.findById(id);
    }

    public Optional<VaccinatedUser> findVaccinatedByCin(String cin) {
        return vaccinatedRepository.findAll()
                .stream()
                .filter(vaccinatedUser -> vaccinatedUser.getAppUserDetails().getCin().equals(cin))
                .findAny();
    }

    public void insert(VaccinatedUser vaccinatedUser) {
        findVaccinatedByCin(vaccinatedUser.getAppUserDetails().getCin())
                .ifPresentOrElse($ -> {
                    throw new IllegalStateException("Already vaccinated");
                }, () -> vaccinatedRepository.save(vaccinatedUser));
    }

    public void deleteByCin(String cin) {
        findVaccinatedByCin(cin)
                .ifPresentOrElse(vaccinatedRepository::delete, () -> {
                    throw new IllegalStateException("Vaccinated with CIN " + cin + " does not exist");
                });
    }

    public void delete(VaccinatedUser vaccinatedUser) {
        vaccinatedRepository.delete(vaccinatedUser);
    }

    public void update(VaccinatedUser vaccinatedUser) {
        vaccinatedRepository.save(vaccinatedUser);
    }

    @Transactional
    public VaccinatedUser update(String cin,
                                 String firstName,
                                 String lastName,
                                 String phoneNumber,
                                 String address,
                                 String illness,
                                 String medicine,
                                 Boolean pregnant,
                                 String gender,
                                 LocalDate birthDate,
                                 City city,
                                 Facility facility) {
        final VaccinatedUser vaccinated = findVaccinatedByCin(cin)
                .orElseThrow(() -> new IllegalStateException("Vaccinated user with CIN " + cin + " does not exist"));

        if (vaccinated != null) {
            if (firstName != null && !firstName.isBlank())
                vaccinated.getAppUserDetails().setFirstName(firstName);
            if (lastName != null && !lastName.isBlank())
                vaccinated.getAppUserDetails().setLastName(lastName);
            if (address != null && !address.isBlank())
                vaccinated.setAddress(address);
            if (gender != null && !gender.isBlank())
                vaccinated.setGender(gender);
            if (city != null)
                vaccinated.setCity(city);
            if (facility != null)
                vaccinated.setFacility(facility);
            if (illness != null && !illness.isBlank())
                vaccinated.setIllness(illness);
            if (medicine != null && !medicine.isBlank())
                vaccinated.setMedicine(medicine);
            if (birthDate != null)
                vaccinated.setBirthDate(birthDate);
            if (pregnant != null)
                vaccinated.setPregnant(pregnant);
            if (phoneNumber != null && !phoneNumber.isBlank())
                vaccinated.setPhoneNumber(phoneNumber);
            vaccinatedRepository.save(vaccinated);
        }
        return vaccinated;
    }
}
