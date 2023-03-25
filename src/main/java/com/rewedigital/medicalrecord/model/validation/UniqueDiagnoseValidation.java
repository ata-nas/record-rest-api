package com.rewedigital.medicalrecord.model.validation;

import com.rewedigital.medicalrecord.model.validation.validator.UniqueDiagnoseValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = UniqueDiagnoseValidator.class)
public @interface UniqueDiagnoseValidation {

    String message() default "Diagnose already exists!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
