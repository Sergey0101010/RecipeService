package com.sergey.recipesservice.recipes.businesslayer;

import com.sergey.recipesservice.recipes.dto.RequestUserDto;


public interface UserService {

    public void newUserRegister(RequestUserDto requestUserDto);

}
