package com.salesianos.triana.dam.MiarmaProyect.validations.simple.anotations;

import com.salesianos.triana.dam.MiarmaProyect.validations.simple.validations.UniqueUserValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueUserValidator.class)
@Documented
public @interface UniqueUser {

    String message() default "El valor de este campo debe ser único";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
