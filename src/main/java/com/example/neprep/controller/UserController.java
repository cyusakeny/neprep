package com.example.neprep.controller;

import com.example.neprep.models.User;
import com.example.neprep.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequestMapping(path ="/user")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/addUser")
    public String AddUser(@ModelAttribute("user") User userData){
        System.out.println("Data:"+userData.getEmail());
            User user = userService.AddUser(userData);
            if (user==null){
                return "redirect:/register";
            }
            return "redirect:/signin";


    }
}
