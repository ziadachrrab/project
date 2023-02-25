package com.projet.PFE.Repositories;

import com.projet.PFE.Entities.centreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface centreRepository extends JpaRepository<centreEntity, String> {
    @Query("SELECT c FROM centreEntity c WHERE c.nom = ?1")
    Optional<centreEntity> findByName(String nom);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM centreEntity WHERE nom = ?1")
    void deleteByName(String nom);
}
