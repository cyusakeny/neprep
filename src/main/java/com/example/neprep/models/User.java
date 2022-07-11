package com.example.neprep.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String email;
    private String firstName;
    private String lastName;
    private  String password;
    private String phone;
    private int nationalId;
    private String role;

    public User() {
    }

    public User(String email, String firstName, String lastName, String password, String phone, int nationalId) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.phone = phone;
        this.nationalId = nationalId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public void setId(UUID id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setNationalId(int nationalId) {
        this.nationalId = nationalId;
    }

    public void setRole(String role) {
        this.role = role;
    }
    public UUID getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public int getNationalId() {
        return nationalId;
    }

    public String getRole() {
        return role;
    }
}
