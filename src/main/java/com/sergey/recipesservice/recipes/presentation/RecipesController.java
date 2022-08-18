package com.sergey.recipesservice.recipes.presentation;

import com.sergey.recipesservice.recipes.businesslayer.RecipeServiceImpl;
import com.sergey.recipesservice.recipes.databaselayer.Recipe;
import com.sergey.recipesservice.recipes.dto.RecipeId;
import com.sergey.recipesservice.recipes.dto.ResponseRecipeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Validated
public class RecipesController {

    final RecipeServiceImpl recipeServiceImpl;

    @GetMapping("/recipe/{id}")
    public ResponseRecipeDto getRecipe(@PathVariable Long id) {
        ResponseRecipeDto responseRecipeDto = recipeServiceImpl.getRecipe(id);
        return responseRecipeDto;
    }

    @PostMapping("/recipe/new")
    public RecipeId postRecipe(@Valid @RequestBody Recipe recipe,
                               @AuthenticationPrincipal UserDetails details) {
        RecipeId recipeId = recipeServiceImpl.postRecipe(recipe, details);
        return recipeId;
    }

    @DeleteMapping("/recipe/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRecipe(@PathVariable Long id,
                             @AuthenticationPrincipal UserDetails details) {
        recipeServiceImpl.deleteRecipe(id, details);
    }

    @PutMapping("/recipe/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateRecipe(@Valid @RequestBody Recipe recipe,
                             @PathVariable Long id,
                             @AuthenticationPrincipal UserDetails details) {

        recipeServiceImpl.updateRecipe(recipe, id, details);
    }

    @GetMapping("/recipe/search")
    public List<ResponseRecipeDto> getAllParametrizedRecipes(@RequestParam
                                                     Map<String, String> allParams) {
        return recipeServiceImpl.getAllParametrizedRecipes(allParams);
    }


}
