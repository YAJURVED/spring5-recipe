package guru.springframework.recipe.aspect;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

import guru.springframework.recipe.annotation.RecipeCacheable;

@Aspect
@Configuration
public class RecipeAspect {

	// 	The pointcut will look for your annotation ( this case RecipeAspect )
	@Pointcut("@annotation(recipeCacheable)")
	public void annotationPointCutDefinition(RecipeCacheable recipeCacheable) {
	}
	
	// The pointcut is a catch-all pointcut with the scope of execution
	@Pointcut("execution(* *(..))")
	public void atExecution()	{
	}

	@Around("annotationPointCutDefinition(recipeCacheable) && atExecution()")
	public Object businessOperation(ProceedingJoinPoint joinPoint, RecipeCacheable recipeCacheable) throws Throwable {
	//@Around("@annotation(guru.springframework.recipe.annotation.RecipeCacheable)")
	//public void businessOperation(ProceedingJoinPoint joinPoint) throws Throwable {
		
		long start = System.currentTimeMillis();
		Object proceed = joinPoint.proceed();
		Object args[] = joinPoint.getArgs();
		long executionTime = System.currentTimeMillis() - start;
		System.out.println("Time take by arround aspect : " + executionTime);
		return joinPoint.proceed();
	}
}
