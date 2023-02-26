package com.projet.pfe.controllers;

import com.projet.pfe.entities.AppUserDetails;
import com.projet.pfe.entities.UserRole;
import com.projet.pfe.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin")
public class AdminController {
    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public List<AppUserDetails> admins(){
        return userService.getAdmins();
    }

    @GetMapping("/find/{cin}")
    public AppUserDetails byCin(@PathVariable("cin") String cin){
        final AppUserDetails result = userService.findUserByCin(cin);
        if (result != null && result.getUserRole().equals(UserRole.ADMIN))
            return result;
        return null;
    }

    @PostMapping("/add")
    public void registerNewAdmin(@RequestBody AppUserDetails appUserDetails){
        userService.insert(appUserDetails);
    }


    @PutMapping( "/update/{adminCin}")
    public void updateAdmin(@PathVariable("adminCin") String cin,
                            @RequestParam(required = false) String firstName,
                            @RequestParam(required = false) String lastName,
                            @RequestParam(required = false) String email,
                            @RequestParam(required = false) String role,
                            @RequestParam(required = false) String password) {
        userService.update(cin, firstName, lastName, email, role, password);
    }

    @DeleteMapping("/delete/{adminCin}")
    public void deleteAdmin(@PathVariable("adminCin")String cin ){
        userService.deleteByCin(cin);
    }
}
