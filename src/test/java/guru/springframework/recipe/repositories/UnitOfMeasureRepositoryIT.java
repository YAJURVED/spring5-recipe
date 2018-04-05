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

//https://spring.io/blog/2016/04/15/testing-improvements-in-spring-boot-1-4

@RunWith(SpringRunner.class)	
// tells JUnit to run using Spring’s testing support

@DataJpaTest 
// another database and configure spring data jpa
/*
 * 	Configure an in-memory database.
	Auto-configure Hibernate, Spring Data and the DataSource.
	Perform an @EntityScan.
	Turn on SQL logging
 * */

//@SpringBootTest 
//first attempt to load @Configuration from any inner-classes, and if that fails, 
//it will search for your primary @SpringBootApplication class.

//@WebMvcTest(UserVehicleController.class)
/*
 * 	Auto-configure Spring MVC, Jackson, Gson, Message converters etc.
	Load relevant components (@Controller, @RestController, @JsonComponent etc)
	Configure MockMVC
 * */
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
