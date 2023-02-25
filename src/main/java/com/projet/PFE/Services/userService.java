package com.projet.PFE.Services;

import com.projet.PFE.Entities.userEntity;
import com.projet.PFE.Repositories.userRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class userService {
    private final userRepository UserRepository;

    public userService(userRepository UserRepository) {
        this.UserRepository = UserRepository;
    }

    public List<userEntity> getUsers(){
        return UserRepository.findAll();
    }

    public userEntity findUserByCin(String cin){
        return UserRepository.findByCin(cin).orElseThrow(() -> new IllegalStateException("User not found"));
    }

    public void addUserEntity(userEntity UserEntity){
        Optional<userEntity> userEntityOptional = UserRepository.findByCin(UserEntity.getCin());
        if(userEntityOptional.isPresent()){
            throw new IllegalStateException("User already exists");
        }
        UserRepository.save(UserEntity);
    }

    public void deleteUserEntity(String cin){
        boolean exists = UserRepository.findByCin(cin).isPresent();
        if(!exists){
            throw new IllegalStateException("User with CIN "+cin+" doesn't exist");
        }
        UserRepository.deleteByCin(cin);
    }

    @Transactional
    public void updateUserEntity(String role, String cin, String password){
        userEntity user = UserRepository.findByCin(cin).orElseThrow(() -> new IllegalStateException("User with CIN "+ cin + " does not exist"));
        if(role != null && role.length() > 0 && !Objects.equals(user.getRole(), role)){
            user.setRole(role);
        }

        if(cin != null && cin.length() > 0 && !Objects.equals(user.getCin(), cin)) {
            Optional<userEntity> userEntityOptional = UserRepository.findByCin(cin);
            if(userEntityOptional.isPresent()){
                throw new IllegalStateException("CIN " +cin+" already exists");
            }
            user.setPassword(password);
        }
    }
}
