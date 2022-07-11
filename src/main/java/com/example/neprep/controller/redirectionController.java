package com.example.neprep.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class redirectionController {
    @GetMapping("/")
    public String openPage(){
        return "register.html";
    }
    @GetMapping("/signin")
    public String Login(){
        return ("login.html");
    }
}
