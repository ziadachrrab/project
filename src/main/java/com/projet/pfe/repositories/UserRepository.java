package com.projet.pfe.repositories;

import com.projet.pfe.entities.AppUserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<AppUserDetails, Long> {

    Optional<AppUserDetails> findByCin(String CIN);

    Optional<AppUserDetails> findByUsername(String username);

}
