package com.rewedigital.medicalrecord.model.validation.validator;

import com.rewedigital.medicalrecord.model.validation.NotConflictingPricingHistoryDatesValidation;
import com.rewedigital.medicalrecord.repository.PricingHistoryRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;

import java.time.LocalDate;

@RequiredArgsConstructor
public class NotConflictingPricingHistoryDatesValidator implements ConstraintValidator<NotConflictingPricingHistoryDatesValidation, Object> {

    private String first;
    private String second;
    private final PricingHistoryRepository pricingHistoryRepository;


    @Override
    public void initialize(NotConflictingPricingHistoryDatesValidation constraintAnnotation) {
        first = constraintAnnotation.first();
        second = constraintAnnotation.second();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        BeanWrapper beanWrapper = PropertyAccessorFactory.forBeanPropertyAccess(value);

        Object firstValue = beanWrapper.getPropertyValue(first);
        Object secondValue = beanWrapper.getPropertyValue(second);

        if (!(firstValue instanceof LocalDate toCheckFistValue) ||
                !(secondValue instanceof LocalDate toCheckSecondValue)
        ) {
            return false;
        }
        return pricingHistoryRepository.findConflictingDates(toCheckFistValue, toCheckFistValue).isEmpty();
    }

}
