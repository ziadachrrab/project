package com.projet.PFE.Repositories;

import com.projet.PFE.Entities.vaccinatedEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface vaccinatedRepository extends JpaRepository<vaccinatedEntity, String> {
    @Query("SELECT v FROM vaccinatedEntity v WHERE v.cin = ?1 ")
    Optional<vaccinatedEntity> findByCin(String cin);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM vaccinatedEntity WHERE cin = ?1")
    void deleteByCin(String cin);
}
