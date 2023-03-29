package com.rewedigital.medicalrecord.model.validation.validator;

import com.rewedigital.medicalrecord.model.validation.ExistingPricingHistoryIssueNoValidation;
import com.rewedigital.medicalrecord.repository.PricingHistoryRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ExistingPricingHistoryIssueNoValidator implements ConstraintValidator<ExistingPricingHistoryIssueNoValidation, String> {

    private final PricingHistoryRepository pricingHistoryRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value == null || pricingHistoryRepository.findByIssueNo(value).isPresent();
    }

}
