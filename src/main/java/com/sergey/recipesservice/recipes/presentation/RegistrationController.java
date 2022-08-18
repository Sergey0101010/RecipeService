package com.sergey.recipesservice.recipes.presentation;

import com.sergey.recipesservice.recipes.businesslayer.UserServiceImpl;
import com.sergey.recipesservice.recipes.dto.RequestUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Validated
public class RegistrationController {

    final UserServiceImpl userServiceImpl;


    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    public void newUserRegister(@Valid @RequestBody RequestUserDto requestUserDto) {
        userServiceImpl.newUserRegister(requestUserDto);
    }

}
