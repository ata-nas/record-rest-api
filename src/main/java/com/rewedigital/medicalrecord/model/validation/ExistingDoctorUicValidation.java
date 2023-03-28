package com.rewedigital.medicalrecord.model.validation;

import com.rewedigital.medicalrecord.model.validation.validator.ExistingDoctorUicValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Valid if exist in DB, Not Valid if not exist in DB.
 * Null values are considered valid!
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Constraint(validatedBy = ExistingDoctorUicValidator.class)
public @interface ExistingDoctorUicValidation {

    String message() default "Doctor with given {uic} does not exist!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
