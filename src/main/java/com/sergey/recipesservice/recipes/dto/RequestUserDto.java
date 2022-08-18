package com.sergey.recipesservice.recipes.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestUserDto {

    @Email
    @Pattern(regexp = "[^@]+@[^@]+\\.[^@.]+")
    @NotBlank
    private String email;

    @Size(min = 8)
    @NotBlank
    private String password;


}
