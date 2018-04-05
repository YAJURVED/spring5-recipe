package guru.springframework.recipe.services;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import guru.springframework.recipe.annotation.RecipeCacheable;
import guru.springframework.recipe.domain.Recipe;
import guru.springframework.recipe.repositories.RecipeRepository;

//@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

	private RecipeRepository recipeRepository;

	public RecipeServiceImpl(RecipeRepository recipeRepository) {
		this.recipeRepository = recipeRepository;
	}

	@RecipeCacheable
	@Override
	public Set<Recipe> getRecipes() {
		
		//log.info("*** Inside getRecipes ****");
		
		Set<Recipe> recipes = new HashSet<>();
		
		recipeRepository.findAll().iterator().forEachRemaining(e -> recipes.add(e));
		
		return recipes;
	}

	@Override
	public Recipe findRecipeById(Long id) {
		Optional<Recipe> recipe = recipeRepository.findById(id);
		if(!recipe.isPresent())
			throw new RuntimeException("Recipe not present !!!");
		return recipe.get();
	}

}
