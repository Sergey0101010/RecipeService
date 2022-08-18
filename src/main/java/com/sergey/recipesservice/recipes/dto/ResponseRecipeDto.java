package com.sergey.recipesservice.recipes.dto;

import com.sergey.recipesservice.recipes.databaselayer.Recipe;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseRecipeDto {
    private String name;
    private String category;
    private LocalDateTime date;
    private String description;
    private List<String> ingredients;
    private List<String> directions;

    public void convertFromRecipe(Recipe recipe) {
        this.name = recipe.getName();
        this.category = recipe.getCategory();
        this.date = recipe.getDateOfCreationOrUpdate();
        this.description = recipe.getDescription();
        this.ingredients = recipe.getIngredients();
        this.directions = recipe.getDirections();
    }

}
