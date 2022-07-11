package com.example.neprep.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class SigninDto {
    @NotBlank
    @Email
    private  String username;
    @NotBlank
    private  String password;
}
