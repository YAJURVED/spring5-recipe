package guru.springframework.recipe.services;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyLong;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import guru.springframework.recipe.domain.Recipe;
import guru.springframework.recipe.repositories.RecipeRepository;

public class RecipeServiceImplTest {

	RecipeServiceImpl recipeService;

	@Mock
	RecipeRepository recipeRepository;

	@Before
	public void setUp()	{
		MockitoAnnotations.initMocks(this);
		recipeService = new RecipeServiceImpl(recipeRepository);
	}
	
	@Test
	public void getRecipeByIdTest()	{
		Recipe r1 = new Recipe();
		Optional<Recipe> rOpt = Optional.of(r1);
		when(recipeRepository.findById(1L)).thenReturn(rOpt);
		Recipe recipe = recipeService.findRecipeById(1L);
		Assertions.assertThat(recipe).isNotNull();
		verify(recipeRepository,times(1)).findById(anyLong());
		verify(recipeRepository, never()).findAll();
		
	}
	
	@Test
	public void testGetRecipes() {
		
		Set<Recipe> recipesData = new HashSet<>();
		recipesData.add(new Recipe());
		when(recipeService.getRecipes()).thenReturn(recipesData);
		
		Set<Recipe> recipes = recipeService.getRecipes();
		Assertions.assertThat(recipes.size()).isEqualTo(1);
		verify(recipeRepository, times(1)).findAll();
	}

}
