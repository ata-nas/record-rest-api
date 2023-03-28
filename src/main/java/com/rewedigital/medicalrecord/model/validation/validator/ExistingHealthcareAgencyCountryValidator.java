package com.rewedigital.medicalrecord.model.validation.validator;

import com.rewedigital.medicalrecord.model.validation.ExistingHealthcareAgencyCountryValidation;
import com.rewedigital.medicalrecord.repository.HealthcareAgencyRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ExistingHealthcareAgencyCountryValidator implements ConstraintValidator<ExistingHealthcareAgencyCountryValidation, String> {

    private final HealthcareAgencyRepository healthcareAgencyRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value == null || healthcareAgencyRepository.findByCountry(value).isPresent();
    }

}
