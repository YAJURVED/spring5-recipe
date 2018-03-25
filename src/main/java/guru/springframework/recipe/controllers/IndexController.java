package guru.springframework.recipe.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import guru.springframework.recipe.services.RecipeService;

@Controller
public class IndexController {

	private RecipeService recipeService;

	public IndexController(RecipeService recipeService) {
		this.recipeService = recipeService;
	}

	@GetMapping({ "", "/", "/index" })
	public String getIndexPage() {
		return "index";
	}
	
	@GetMapping("/recipe/all")
	public String getAllRecipes(Model model)	
	{
		model.addAttribute("recipes", recipeService.getRecipes());
		return "recipe";
	}
}
