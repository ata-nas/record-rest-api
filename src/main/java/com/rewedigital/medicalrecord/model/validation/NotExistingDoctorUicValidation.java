package com.rewedigital.medicalrecord.model.validation;

import com.rewedigital.medicalrecord.model.validation.validator.NotExistingDoctorUicValidator;
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
@Constraint(validatedBy = NotExistingDoctorUicValidator.class)
public @interface NotExistingDoctorUicValidation {

    String message() default "Doctor already exists!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
