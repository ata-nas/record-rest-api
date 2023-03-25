package com.rewedigital.medicalrecord.model.validation;

import com.rewedigital.medicalrecord.model.validation.validator.ExistingPatientUicValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = ExistingPatientUicValidator.class)
public @interface ExistingPatientUicValidation {

    String message() default "Patient with given 'uic' does not exist!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
