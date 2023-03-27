package com.rewedigital.medicalrecord.model.validation;

import com.rewedigital.medicalrecord.model.validation.validator.StartDateBeforeEndDateValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = StartDateBeforeEndDateValidator.class)
public @interface StartDateBeforeEndDateValidation {

    String first();

    String second();

    String message() default "StartDate must be before EndDate!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
