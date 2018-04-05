package guru.springframework.recipe.controllers;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import guru.springframework.recipe.domain.Recipe;
import guru.springframework.recipe.services.RecipeService;

// Unit test for recipe controller
public class RecipeControllerTest {

	private RecipeController controller;
	
	@Mock
	RecipeService recipeService;
	
	@Before
	public void setUp()	{
		MockitoAnnotations.initMocks(this);
		controller = new RecipeController(recipeService);
	}
	
	@Test
	public void testGetRecipe() throws Exception	{
		when(recipeService.findRecipeById(anyLong())).thenReturn(new Recipe());
		
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		mockMvc.perform(get("/recipe/show/1"))
			.andExpect(status().isOk())
			.andExpect(view().name("show"));
	}
	
}
