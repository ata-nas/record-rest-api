package com.rewedigital.medicalrecord.model.validation;

import com.rewedigital.medicalrecord.model.validation.validator.NotExistingSpecialtyNameValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Valid if not exist in DB, Not Valid if exist in DB.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = NotExistingSpecialtyNameValidator.class)
public @interface NotExistingSpecialtyNameValidation {

    String message() default "Specialty already exists!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
