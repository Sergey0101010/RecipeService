package com.sergey.recipesservice.recipes.businesslayer;

import com.sergey.recipesservice.recipes.databaselayer.User;
import com.sergey.recipesservice.recipes.dto.RequestUserDto;
import com.sergey.recipesservice.recipes.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


    public void newUserRegister(RequestUserDto requestUserDto) {

        if (userRepository.existsUserByEmail(requestUserDto.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } else {
            User newUser = new User();
            newUser.setEmail(requestUserDto.getEmail());
            newUser.setPassword(passwordEncoder.encode(requestUserDto.getPassword()));

            userRepository.save(newUser);
        }
    }

}
