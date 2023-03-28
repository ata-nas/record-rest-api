package com.rewedigital.medicalrecord.model.validation;

import com.rewedigital.medicalrecord.model.validation.validator.NotExistingDiagnoseNameValidator;

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
@Constraint(validatedBy = NotExistingDiagnoseNameValidator.class)
public @interface NotExistingDiagnoseNameValidation {

    String message() default "Diagnose already exists!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
