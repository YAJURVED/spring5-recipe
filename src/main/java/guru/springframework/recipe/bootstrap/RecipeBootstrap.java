package guru.springframework.recipe.bootstrap;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.assertj.core.util.Arrays;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import guru.springframework.recipe.domain.Category;
import guru.springframework.recipe.domain.Difficulty;
import guru.springframework.recipe.domain.Ingredient;
import guru.springframework.recipe.domain.Notes;
import guru.springframework.recipe.domain.Recipe;
import guru.springframework.recipe.domain.UnitOfMeasure;
import guru.springframework.recipe.repositories.CategoryRepository;
import guru.springframework.recipe.repositories.RecipeRepository;
import guru.springframework.recipe.repositories.UnitOfMeasureRepository;


/*
 * H2 db query
 * SELECT * FROM CATEGORY, INGREDIENT , UNIT_OF_MEASURE, RECIPE, NOTES, RECIPE_CATEGORY
WHERE UNIT_OF_MEASURE_ID = UNIT_OF_MEASURE.ID
AND INGREDIENT.RECIPE_ID = RECIPE.ID
AND RECIPE.NOTES_ID = NOTES.ID
AND RECIPE_CATEGORY.RECIPE_ID = RECIPE.ID
AND RECIPE_CATEGORY.CATEGORY_ID = CATEGORY.ID
 * */

@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent>{
	private CategoryRepository categoryRepository;
	private RecipeRepository recipeRepository;
	private UnitOfMeasureRepository unitOfMeasureRepository;

	public RecipeBootstrap(CategoryRepository categoryRepository, RecipeRepository recipeRepository,
			UnitOfMeasureRepository unitOfMeasureRepository) {
		this.categoryRepository = categoryRepository;
		this.recipeRepository = recipeRepository;
		this.unitOfMeasureRepository = unitOfMeasureRepository;
	}

	@Override
	@Transactional
	public void onApplicationEvent(ContextRefreshedEvent event) {
		// check to insert the mock data once in db
		if(!recipeRepository.findAll().iterator().hasNext())
			recipeRepository.saveAll(getRecipes());
	}
	
	private List<Recipe> getRecipes()	{
		
		List<Recipe> recipes = new ArrayList<>();
		
		Optional<UnitOfMeasure> tableSpoon = unitOfMeasureRepository.findByDescription("Tablespoon");
		Optional<UnitOfMeasure> Teaspoon = unitOfMeasureRepository.findByDescription("Teaspoon");
		Optional<UnitOfMeasure> cups = unitOfMeasureRepository.findByDescription("Cups");
		Optional<UnitOfMeasure> pint = unitOfMeasureRepository.findByDescription("Pint");
		Optional<UnitOfMeasure> dash = unitOfMeasureRepository.findByDescription("Dash");
		
		Optional<Category> mexicanCategory = categoryRepository.findByDescription("Mexican");

		
		Ingredient chilipowder = new Ingredient();
		chilipowder.setAmount(new BigDecimal("2"));
		chilipowder.setDescription("chili powder");
		chilipowder.setUnitOfMeasure(tableSpoon.get());

		Ingredient gingergarlic = new Ingredient();
		gingergarlic.setAmount(new BigDecimal("3"));
		gingergarlic.setDescription("ginger garlic");
		gingergarlic.setUnitOfMeasure(tableSpoon.get());

		
		Notes notes = new Notes();
		notes.setNotes("notes to cook spicy grilled chicken tacos");

		//for(int i = 0; i < 50; i++)
		{
		Recipe spicyGrilledChickenTacos = new Recipe();
		spicyGrilledChickenTacos.setCookTime(1);
		spicyGrilledChickenTacos.setDescription("Spicy Grilled Chicken Tacos");
		spicyGrilledChickenTacos.setDirections("");
		//spicyGrilledChickenTacos.setImage(image);
		spicyGrilledChickenTacos.setPrepTime(1);
		spicyGrilledChickenTacos.setServings(6);
		spicyGrilledChickenTacos.setSource("simplerecipes.com");
		spicyGrilledChickenTacos.setUrl("simplerecipes.com");

		spicyGrilledChickenTacos.setDifficulty(Difficulty.EASY);
		spicyGrilledChickenTacos.setNotes(notes);
		
		spicyGrilledChickenTacos.addIngredient(chilipowder);
		spicyGrilledChickenTacos.addIngredient(gingergarlic);
		
		//spicyGrilledChickenTacos.getIngredients().add(chilipowder);
		//spicyGrilledChickenTacos.getIngredients().add(gingergarlic);
		Set<Category> categories = new HashSet<>();
		categories.add(mexicanCategory.get());
		//spicyGrilledChickenTacos.getCategories().add(mexicanCategory.get());
		spicyGrilledChickenTacos.setCategories(categories);

		//chilipowder.setRecipe(spicyGrilledChickenTacos);
		//gingergarlic.setRecipe(spicyGrilledChickenTacos);
		//notes.setRecipe(spicyGrilledChickenTacos);

		recipes.add(spicyGrilledChickenTacos);
		}
		return recipes;
	}



}
