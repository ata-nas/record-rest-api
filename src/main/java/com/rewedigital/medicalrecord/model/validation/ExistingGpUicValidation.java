package com.rewedigital.medicalrecord.model.validation;

import com.rewedigital.medicalrecord.model.validation.validator.ExistingGpUicValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Valid if exist in DB, Not Valid if not exist or soft deleted in DB.
 * Null values are considered valid!
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = ExistingGpUicValidator.class)
public @interface ExistingGpUicValidation {

    String message() default "Gp with given {uic} does not exist!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
