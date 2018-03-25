package guru.springframework.recipe.repositories;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import guru.springframework.recipe.domain.UnitOfMeasure;

@RunWith(SpringRunner.class)
@DataJpaTest // another database and configure spring data jpa
public class UnitOfMeasureRepositoryIT {

	@Autowired
	UnitOfMeasureRepository unitOfMeasureRepository;
	
	@Before
	public void setUp()	{
		
	}
	
	@Test
	public void testFindByDescription() {
	 	Optional<UnitOfMeasure> unitOfMeasure = unitOfMeasureRepository.findByDescription("Teaspoon");
	 	Assertions.assertThat(unitOfMeasure.get().getDescription()).isEqualTo("Teaspoon");
	}

	@Test
	public void testFindCupByDescription() {
	 	Optional<UnitOfMeasure> unitOfMeasure = unitOfMeasureRepository.findByDescription("Cups");
	 	Assertions.assertThat(unitOfMeasure.get().getDescription()).isEqualTo("Cups");
	}

}
