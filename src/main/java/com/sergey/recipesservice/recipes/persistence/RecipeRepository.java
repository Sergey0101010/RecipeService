package com.sergey.recipesservice.recipes.persistence;

import com.sergey.recipesservice.recipes.databaselayer.Recipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Long> {


    List<Recipe> findAllByCategoryIgnoreCase(@NotBlank String category);

    List<Recipe> findAllByNameContainingIgnoreCase(@NotBlank String name);
}
