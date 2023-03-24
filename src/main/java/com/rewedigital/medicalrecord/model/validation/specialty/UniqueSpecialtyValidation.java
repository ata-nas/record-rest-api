package com.rewedigital.medicalrecord.model.validation.specialty;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = UniqueSpecialtyValidator.class)
public @interface UniqueSpecialtyValidation {

    String message() default "Specialty already exists!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
