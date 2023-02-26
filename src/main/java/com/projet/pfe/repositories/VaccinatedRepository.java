package com.projet.pfe.repositories;

import com.projet.pfe.entities.VaccinatedUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VaccinatedRepository extends JpaRepository<VaccinatedUser, Long> {


}
