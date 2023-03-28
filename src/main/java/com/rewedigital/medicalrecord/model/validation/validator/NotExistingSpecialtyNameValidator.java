package com.rewedigital.medicalrecord.model.validation.validator;

import com.rewedigital.medicalrecord.model.validation.NotExistingSpecialtyNameValidation;
import com.rewedigital.medicalrecord.repository.SpecialtyRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NotExistingSpecialtyNameValidator implements ConstraintValidator<NotExistingSpecialtyNameValidation, String> {

    private final SpecialtyRepository specialtyRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return specialtyRepository.findByName(value).isEmpty();
    }

}
