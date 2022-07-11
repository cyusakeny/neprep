package com.example.neprep.service;

import com.example.neprep.models.User;
import com.example.neprep.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
public User AddUser(User user){
    return userRepository.save(user);
}

}
