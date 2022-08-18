package com.sergey.recipesservice.recipes.persistence;

import com.sergey.recipesservice.recipes.databaselayer.User;
import org.springframework.data.repository.CrudRepository;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public interface UserRepository extends CrudRepository<User, Long> {
    boolean existsUserByEmailAndPassword(@NotBlank @Email String email, @Size(min = 8) String password);

    boolean existsUserByEmail(@NotBlank @Email String email);

    User findUserByEmail(@NotBlank @Email String email);

    User findByEmail(@NotBlank @Email String email);

}
