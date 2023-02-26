package com.projet.pfe.services;

import com.projet.pfe.entities.AppUserDetails;
import com.projet.pfe.entities.UserRole;
import com.projet.pfe.repositories.SupervisorRepository;
import com.projet.pfe.repositories.UserRepository;
import com.projet.pfe.repositories.VaccinatedRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElse(null);
    }

    public List<AppUserDetails> findAll() {
        return userRepository.findAll();
    }

    public Optional<AppUserDetails> find(long id) {
        return userRepository.findById(id);
    }

    public List<AppUserDetails> getUsers() {
        return findAll().stream()
                .filter(user -> user.getUserRole().equals(UserRole.USER))
                .toList();
    }

    public List<AppUserDetails> getAdmins() {
        return findAll().stream()
                .filter(user -> user.getUserRole().equals(UserRole.ADMIN))
                .toList();
    }

    public AppUserDetails findUserByCin(String cin) {
        return userRepository.findByCin(cin)
                .orElseThrow(() -> new IllegalStateException("User not found"));
    }

    public void insert(AppUserDetails appUserDetails) {
        userRepository.findById(appUserDetails.getId())
                .ifPresentOrElse($ -> {
                    throw new IllegalStateException("User already exists!");
                }, () -> userRepository.save(appUserDetails));
        ;
    }

    public void delete(AppUserDetails appUserDetails) {
        if (appUserDetails == null)
            return;
        userRepository.delete(appUserDetails);
    }

    public void deleteByCin(String cin) {
        userRepository.findByCin(cin)
                .ifPresentOrElse(this::delete, () -> {
                    throw new IllegalStateException("User with CIN " + cin + " doesn't exist");
                });
    }

    public AppUserDetails update(AppUserDetails appUserDetails) {
        return userRepository.save(appUserDetails);
    }

    public AppUserDetails update(String cin,
                                 String firstName,
                                 String lastName,
                                 String email,
                                 String password,
                                 String role) {
        final AppUserDetails appUserDetails = findUserByCin(cin);

        if (appUserDetails != null) {
            if (firstName != null && !firstName.isBlank())
                appUserDetails.setFirstName(firstName);
            if (lastName != null && !lastName.isBlank())
                appUserDetails.setLastName(lastName);
            if (email != null && !email.isBlank())
                appUserDetails.setEmail(email);
            if (password != null && !password.isBlank())
                appUserDetails.setPassword(password);
            if (role != null && !role.isBlank())
                appUserDetails.setUserRole(UserRole.valueOf(role));
            userRepository.save(appUserDetails);
        }
        return appUserDetails;
    }
}
