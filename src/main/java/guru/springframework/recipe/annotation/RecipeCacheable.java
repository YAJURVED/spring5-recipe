package guru.springframework.recipe.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RecipeCacheable {
	public String reqKey() default "policyNumber";
	public String resKey() default "SDIPFormalInsuranceInquiryResult.AtlasPolicyKey";

}
