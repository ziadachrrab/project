package com.projet.PFE.Controllers;

import com.projet.PFE.Entities.userEntity;
import com.projet.PFE.Services.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class userController {
    private final userService UserService;
    @Autowired
    public userController(userService UserService) {
        this.UserService = UserService;
    }

    @GetMapping("/all")
    public List<userEntity> getUser(){
        return UserService.getUsers();
    }

    @GetMapping("/find/{cin}")
    public userEntity getUserByCin(@PathVariable("cin") String cin){
        return UserService.findUserByCin(cin);
    }

    @PostMapping("/add")
    public void registerUser(@RequestBody userEntity User){
        UserService.addUserEntity(User);
    }

    @PutMapping("/update/{userCin}")
    public void updateUser(
            @PathVariable("userCin") String cin,
            @RequestParam(required = false) String role,
            @RequestParam(required = false) String password) {
        UserService.updateUserEntity( role, cin, password);
    }

    @DeleteMapping("/delete/{userCin}")
    public void deleteUser(@PathVariable("userCin")String cin ){
        UserService.deleteUserEntity(cin);
    }


}
