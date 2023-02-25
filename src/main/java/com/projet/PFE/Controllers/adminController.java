package com.projet.PFE.Controllers;

import com.projet.PFE.Entities.adminEntity;
import com.projet.PFE.Services.adminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin")
public class adminController {
    private final adminService AdminService;
    @Autowired
    public adminController(adminService AdminService) {
        this.AdminService = AdminService;
    }

    @GetMapping("/all")
    public List<adminEntity> getAdmin(){
        return AdminService.getAdmin();
    }

    @GetMapping("/find/{cin}")
    public adminEntity getAdminByCin(@PathVariable("cin") String cin){
        return AdminService.findAdminByCin(cin);
    }

    @PostMapping("/add")
    public void registerNewAdmin(@RequestBody adminEntity Admin){
        AdminService.addAdminEntity(Admin);
    }


    @PutMapping( "/update/{adminCin}")
    public void updateAdmin(@PathVariable("adminCin") String cin, @RequestParam(required = false) String nom, @RequestParam(required = false) String prenom, @RequestParam(required = false) String email) {
        AdminService.updateAdminEntity(cin, nom, prenom, email);
    }

    @DeleteMapping("/delete/{adminCin}")
    public void deleteAdmin(@PathVariable("adminCin")String cin ){
        AdminService.deleteAdminEntity(cin);
    }
}
