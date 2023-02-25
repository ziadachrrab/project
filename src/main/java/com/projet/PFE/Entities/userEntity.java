package com.projet.PFE.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NoArgsConstructor
@Table
@Getter
@Setter
@ToString
public class userEntity {
    @Id
    @SequenceGenerator(
            name = "admin_sequence",
            sequenceName = "admin_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "admin_sequence"
    )

    private Long Id;
    private String role;

    private String cin;
    private String password;


    public userEntity(String role, String cin, String password) {
        this.role = role;
        this.cin = cin;
        this.password = password;

    }

//
//
//    public Long getId() {
//        return Id;
//    }
//
//    public void setId(Long id) {
//        Id = id;
//    }
//
//    public String getRole() {
//        return role;
//    }
//
//    public void setRole(String role) {
//        this.role = role;
//    }
//
//    public String getCin() {
//        return cin;
//    }
//
//    public void setCin(String cin) {
//        this.cin = cin;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    @Override
//    public String toString() {
//        return "userEntity{" +
//                "Id=" + Id +
//                ", role='" + role + '\'' +
//                ", CIN='" + cin + '\'' +
//                ", password='" + password + '\'' +
//                '}';
//    }
}
