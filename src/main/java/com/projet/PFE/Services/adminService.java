package com.projet.PFE.Services;

import com.projet.PFE.Entities.adminEntity;
import com.projet.PFE.Repositories.adminRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class adminService {
    private final adminRepository AdminRepository;

    public adminService(adminRepository AdminRepository) {
        this.AdminRepository = AdminRepository;
    }

    public List<adminEntity> getAdmin(){
        return AdminRepository.findAll();
    }

    public adminEntity findAdminByCin(String cin){
        return AdminRepository.findByCin(cin).orElseThrow(() -> new IllegalStateException("Admin not found"));
    }

    public void addAdminEntity(adminEntity AdminEntity){
        Optional<adminEntity> adminEntityOptional = AdminRepository.findByCin(AdminEntity.getCin());
        if(adminEntityOptional.isPresent()){
            throw new IllegalStateException("Admin already exists");
        }
        AdminRepository.save(AdminEntity);
    }

    public void deleteAdminEntity(String cin){
        boolean exists = AdminRepository.findByCin(cin).isPresent();
        if(!exists){
            throw new IllegalStateException("Admin with CIN "+cin+" does not exist");
        }
        AdminRepository.deleteByCin(cin);
    }

    @Transactional
    public void updateAdminEntity(String cin, String nom, String prenom, String email) {
        adminEntity admin = AdminRepository.findByCin(cin).orElseThrow(() -> new IllegalStateException("Admin with CIN "+ cin + " does not exist"));
        if(nom != null && nom.length() > 0 && !Objects.equals(admin.getNom(), nom)){
            admin.setNom(nom);
        }
        if(prenom != null && prenom.length() > 0 && !Objects.equals(admin.getPrenom(), prenom)) {
            admin.setPrenom(prenom);
        }
        if(email != null && email.length() > 0 && !Objects.equals(admin.getEmail(), email)) {
            Optional<adminEntity> adminEntityOptional = AdminRepository.findByCin(cin);
            if(adminEntityOptional.isPresent()){
                throw new IllegalStateException("Email taken");
            }
            admin.setEmail(email);
        }

    }
}
