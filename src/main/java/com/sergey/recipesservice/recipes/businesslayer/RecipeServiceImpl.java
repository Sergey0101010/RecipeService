package com.sergey.recipesservice.recipes.businesslayer;

import com.sergey.recipesservice.recipes.databaselayer.Recipe;
import com.sergey.recipesservice.recipes.dto.RecipeId;
import com.sergey.recipesservice.recipes.dto.ResponseRecipeDto;
import com.sergey.recipesservice.recipes.persistence.RecipeRepository;
import com.sergey.recipesservice.recipes.persistence.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService {

    final RecipeRepository recipeRepository;

    @Autowired
    UserRepository userRepository;



    public ResponseRecipeDto getRecipe(Long id) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(id);
        Recipe recipe = recipeOptional.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND));
        ResponseRecipeDto responseRecipeDto = new ResponseRecipeDto(
                recipe.getName(), recipe.getCategory(),
                recipe.getDateOfCreationOrUpdate(),
                recipe.getDescription(), recipe.getIngredients(),
                recipe.getDirections()
        );
        return responseRecipeDto;
    }

    public RecipeId postRecipe(Recipe recipe, UserDetails details) {
        recipe.setRecipeCreator(userRepository.findByEmail(details.getUsername()));
        recipe.getRecipeCreator().setEmail(details.getUsername());
        recipe.getRecipeCreator().setPassword(details.getPassword());
        Recipe savedRecipe = recipeRepository.save(recipe);

        return new RecipeId(savedRecipe.getId());
    }

    public void deleteRecipe(Long id, UserDetails details) {
        if (details.getUsername().equals(recipeRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND))
                .getRecipeCreator()
                .getEmail())) {

            recipeRepository.deleteById(id);

        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

    }

    public void updateRecipe(Recipe recipe, Long id, UserDetails details) {
        if (details.getUsername().equals(recipeRepository.findById(id).orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND))
                .getRecipeCreator().getEmail())) {
            Optional<Recipe> byId = recipeRepository.findById(id);
            Recipe updatedRecipe = byId.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
            updatedRecipe.setName(recipe.getName());
            updatedRecipe.setCategory(recipe.getCategory());
            updatedRecipe.setDescription(recipe.getDescription());
            updatedRecipe.setIngredients(recipe.getIngredients());
            updatedRecipe.setDirections(recipe.getDirections());
            recipeRepository.save(updatedRecipe);
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

    }

    public List<ResponseRecipeDto> getAllParametrizedRecipes(Map<String, String> allparams) {
        if (allparams.size() != 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } else if (!(allparams.containsKey("category") || allparams.containsKey("name"))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } else if (allparams.containsKey("category")) {
            //request by category
            List<Recipe> category =
                    recipeRepository.findAllByCategoryIgnoreCase(allparams.get("category"));
            List<ResponseRecipeDto> responseRecipeDtos = getRecipeDtos(category).stream()
                    .sorted(Comparator.comparing(ResponseRecipeDto::getDate).reversed())
                    .collect(Collectors.toList());
            return responseRecipeDtos;
        } else {
            //request by name
            List<Recipe> name =
                    recipeRepository.findAllByNameContainingIgnoreCase(allparams.get("name"));
            List<ResponseRecipeDto> responseRecipeDtos = getRecipeDtos(name).stream()
                    .sorted(Comparator.comparing(ResponseRecipeDto::getDate).reversed())
                    .collect(Collectors.toList());
            return responseRecipeDtos;
        }

    }


    private List<ResponseRecipeDto> getRecipeDtos(List<Recipe> name) {
        return name.stream().map((recipe -> new ResponseRecipeDto(recipe.getName(),
                recipe.getCategory(),
                recipe.getDateOfCreationOrUpdate(),
                recipe.getDescription(), recipe.getIngredients(),
                recipe.getDirections()))).collect(Collectors.toList());
    }


}
