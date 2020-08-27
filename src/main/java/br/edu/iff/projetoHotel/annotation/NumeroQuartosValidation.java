package br.edu.iff.projetoHotel.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = NumeroQuartosValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NumeroQuartosValidation {
    String message() default "Número dos quartos inválidos.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
