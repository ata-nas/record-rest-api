package com.rewedigital.medicalrecord.model.validation.validator;

import com.rewedigital.medicalrecord.model.validation.ExistingSpecialtyNameValidation;
import com.rewedigital.medicalrecord.repository.SpecialtyRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ExistingSpecialtyNameValidator implements ConstraintValidator<ExistingSpecialtyNameValidation, String> {

    private final SpecialtyRepository specialtyRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value == null || specialtyRepository.findByName(value.toUpperCase()).isPresent();
    }

}
