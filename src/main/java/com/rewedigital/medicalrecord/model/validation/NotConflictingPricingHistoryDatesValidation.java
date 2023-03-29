package com.rewedigital.medicalrecord.model.validation;

import com.rewedigital.medicalrecord.model.validation.validator.NotConflictingPricingHistoryDatesValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = NotConflictingPricingHistoryDatesValidator.class)
public @interface NotConflictingPricingHistoryDatesValidation {

    String first();

    String second();

    String message() default "Given {startDate} and {endDate} period conflicts with already existing one!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};


}
