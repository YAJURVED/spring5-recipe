package guru.springframework.recipe.domain;

import static org.junit.Assert.fail;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

public class CategoryTest {

	Category category;
	
	@Before
	public void setUp()	{
		category = new Category();
	}
	
	@Test
	public void testGetId() {
		category.setId(4L);
		Assertions.assertThat(category.getId().longValue()).isEqualTo(4L);
	}

}
