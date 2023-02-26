package com.projet.pfe;

import com.projet.pfe.entities.*;
import com.projet.pfe.enumerations.City;
import com.projet.pfe.services.FacilityService;
import com.projet.pfe.services.SupervisorService;
import com.projet.pfe.services.UserService;
import com.projet.pfe.services.VaccinatedService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
