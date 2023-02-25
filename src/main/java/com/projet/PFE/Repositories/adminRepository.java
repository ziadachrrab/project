package com.projet.PFE.Repositories;

import com.projet.PFE.Entities.adminEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface adminRepository extends JpaRepository<adminEntity, String> {
    @Query("SELECT a FROM adminEntity a WHERE a.cin = ?1 ")
    Optional<adminEntity> findByCin(String cin);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM adminEntity WHERE cin = ?1")
    void deleteByCin(String cin);
}
