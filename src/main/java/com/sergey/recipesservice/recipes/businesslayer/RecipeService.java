package com.sergey.recipesservice.recipes.businesslayer;

import com.sergey.recipesservice.recipes.databaselayer.Recipe;
import com.sergey.recipesservice.recipes.dto.RecipeId;
import com.sergey.recipesservice.recipes.dto.ResponseRecipeDto;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Map;

public interface RecipeService {
    ResponseRecipeDto getRecipe(Long id);

    RecipeId postRecipe(Recipe recipe, UserDetails details);

    void deleteRecipe(Long id, UserDetails details);

    void updateRecipe(Recipe recipe, Long id, UserDetails details);

    List<ResponseRecipeDto> getAllParametrizedRecipes(Map<String, String> allparams);

}
