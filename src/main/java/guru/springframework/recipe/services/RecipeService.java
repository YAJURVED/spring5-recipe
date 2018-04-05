package guru.springframework.recipe.services;

import java.util.Optional;
import java.util.Set;

import guru.springframework.recipe.domain.Recipe;

public interface RecipeService {
	Set<Recipe> getRecipes();
	Recipe findRecipeById(Long id);
}
