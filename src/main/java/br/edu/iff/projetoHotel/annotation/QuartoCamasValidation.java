package br.edu.iff.projetoHotel.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = QuartoCamasValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface QuartoCamasValidation {
    String message() default "Quatidade de camas inválida.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
