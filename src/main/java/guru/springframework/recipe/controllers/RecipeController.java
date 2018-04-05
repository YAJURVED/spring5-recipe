package guru.springframework.recipe.controllers;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import guru.springframework.recipe.domain.Recipe;
import guru.springframework.recipe.services.RecipeService;

@Controller
public class RecipeController {

	RecipeService recipeService;

	public RecipeController(RecipeService recipeService) {
		this.recipeService = recipeService;
	}

	@GetMapping("/recipe/show/{id}")
	public String getRecipeById(@PathVariable("id") String id, Model model) {
		Recipe recipe = recipeService.findRecipeById(new Long(id));
		model.addAttribute(recipe);
		return "show";
	}
}
