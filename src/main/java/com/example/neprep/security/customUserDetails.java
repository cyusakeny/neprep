package com.example.neprep.security;
import com.example.neprep.models.User;
import com.example.neprep.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class customUserDetails implements UserDetailsService {
@Autowired
 private UserRepository userRepository;

    @Transactional
    public UserDetails loadUserByUsername(String email) {
        User user = userRepository.findByEmail(email);
        if (user!=null) {
            return UserPrincipal.create(user);
        }
        else{
            return null;
        }
    }
    @Transactional
    public  UserDetails loadUserById (UUID id) {
       User user = userRepository.findUserById(id);
       if (user!=null){
           return  UserPrincipal.create(user);
       }
       else {
           return  null;
       }
    }
}
