package com.projet.PFE.Repositories;

import com.projet.PFE.Entities.responsableEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface responsableRepository extends JpaRepository<responsableEntity,String> {
    @Query("SELECT r FROM responsableEntity r WHERE r.cin = ?1 ")
    Optional<responsableEntity> findByCin(String cin);

    @Query("SELECT r FROM responsableEntity r WHERE r.Id = ?1 ")
    Optional<responsableEntity> findById(responsableEntity Id);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM responsableEntity WHERE cin = ?1")
    void deleteByCin(String cin);
}
