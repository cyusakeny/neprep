package com.example.neprep.security;

import com.example.neprep.models.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class UserPrincipal implements UserDetails {
    private UUID Id;

    public UUID getId() {
        return Id;
    }

    public void setId(UUID id) {
        Id = id;
    }

    @JsonIgnore
    private String email;
    private String firstName;
    private String lastName;
    @JsonIgnore
    private String mobile;
    @JsonIgnore
    private String password;
    private  String Role;
    public static  UserPrincipal create(User user){
        return  new UserPrincipal(user.getId(),user.getEmail(), user.getFirstName(), user.getLastName(),
                user.getPhone(), user.getPassword(),user.getRole());
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
