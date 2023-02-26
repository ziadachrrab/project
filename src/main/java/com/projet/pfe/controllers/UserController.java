package com.projet.pfe.controllers;

import com.projet.pfe.entities.AppUserDetails;
import com.projet.pfe.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public List<AppUserDetails> users(){
        return userService.getUsers();
    }

    @GetMapping("/find/{cin}")
    public AppUserDetails byCin(@PathVariable("cin") String cin){
        return userService.findUserByCin(cin);
    }

    @PostMapping("/add")
    public void registerUser(@RequestBody AppUserDetails AppUserDetails){
        userService.insert(AppUserDetails);
    }

    @PutMapping("/update/{cin}")
    public void updateUser(
            @PathVariable("cin") String cin,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String role,
            @RequestParam(required = false) String password) {
        userService.update(cin, firstName, lastName, email, password, role);
    }

    @DeleteMapping("/delete/{cin}")
    public void deleteUser(@PathVariable("cin")String cin ){
        userService.deleteByCin(cin);
    }


}
