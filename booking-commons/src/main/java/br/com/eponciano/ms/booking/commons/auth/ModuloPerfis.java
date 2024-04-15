package br.com.eponciano.ms.booking.commons.auth;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target({ ANNOTATION_TYPE })
public @interface ModuloPerfis {
	
	RoleType[] value() default {};
	RoleType[] perfis() default {};
	
}
