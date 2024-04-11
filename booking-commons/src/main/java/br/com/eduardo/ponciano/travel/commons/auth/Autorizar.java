package br.com.eduardo.ponciano.travel.commons.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.core.annotation.AliasFor;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
public @interface Autorizar {

	@AliasFor("perfisDB")
	ModuloPerfis[] value() default {};
	
	@AliasFor("value")
	ModuloPerfis[] perfisDB() default {};

}
