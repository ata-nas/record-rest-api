package com.rewedigital.medicalrecord.model.validation.tmp;

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
@Constraint(validatedBy = NotOverlappingInsuranceDateValidator.class)
public @interface NotOverlappingInsuranceDateValidation {

    String message() default "Current Insurance Start Date overlaps an already existing one!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
