package com.projet.PFE.Repositories;

import com.projet.PFE.Entities.userEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface userRepository extends JpaRepository<userEntity, String> {
    @Query("SELECT u FROM userEntity u WHERE u.cin = ?1 ")
    Optional<userEntity> findByCin(String CIN);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM userEntity WHERE cin = ?1")
    void deleteByCin(String cin);
}
