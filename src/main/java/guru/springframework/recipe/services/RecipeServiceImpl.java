package guru.springframework.recipe.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import guru.springframework.recipe.domain.Recipe;
import guru.springframework.recipe.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

	private RecipeRepository recipeRepository;

	public RecipeServiceImpl(RecipeRepository recipeRepository) {
		this.recipeRepository = recipeRepository;
	}

	@Override
	public Set<Recipe> getRecipes() {
		
		log.info("*** Inside getRecipes ****");
		
		Set<Recipe> recipes = new HashSet<>();
		
		recipeRepository.findAll().iterator().forEachRemaining(e -> recipes.add(e));
		
		return recipes;
	}

}
