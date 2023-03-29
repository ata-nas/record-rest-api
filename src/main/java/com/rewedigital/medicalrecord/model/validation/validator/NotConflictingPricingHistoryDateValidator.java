package com.rewedigital.medicalrecord.model.validation.validator;

import com.rewedigital.medicalrecord.model.entity.PricingHistoryEntity;
import com.rewedigital.medicalrecord.model.validation.NotConflictingPricingHistoryDateValidation;
import com.rewedigital.medicalrecord.repository.PricingHistoryRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.Optional;

@RequiredArgsConstructor
public class NotConflictingPricingHistoryDateValidator implements ConstraintValidator<NotConflictingPricingHistoryDateValidation, LocalDate> {

    private final PricingHistoryRepository pricingHistoryRepository;

    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        Optional<PricingHistoryEntity> latestDate = pricingHistoryRepository.findLatestPricing();
        return latestDate.isEmpty() || latestDate.get().getFromDate().isBefore(value);
    }

}
