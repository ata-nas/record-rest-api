package com.rewedigital.medicalrecord.model.validation.patient;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = UniquePatientValidator.class)
public @interface UniquePatientValidation {

    String message() default "Patient already exists!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
