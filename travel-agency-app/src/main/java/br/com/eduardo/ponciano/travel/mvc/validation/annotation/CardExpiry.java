package br.com.eduardo.ponciano.travel.mvc.validation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import br.com.eduardo.ponciano.travel.mvc.validation.validator.CardExpiryValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CardExpiryValidator.class)
public @interface CardExpiry {

    String message() default "Data de expiração invalida";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
