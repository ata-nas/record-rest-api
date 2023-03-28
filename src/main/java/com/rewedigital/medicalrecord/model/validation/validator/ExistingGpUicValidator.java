package com.rewedigital.medicalrecord.model.validation.validator;

import com.rewedigital.medicalrecord.model.validation.ExistingGpUicValidation;
import com.rewedigital.medicalrecord.repository.GpRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ExistingGpUicValidator implements ConstraintValidator<ExistingGpUicValidation, String> {

    private final GpRepository gpRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value == null || gpRepository.findByUic(value).isPresent();
    }

}
