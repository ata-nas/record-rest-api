package com.rewedigital.medicalrecord.model.validation.validator;

import com.rewedigital.medicalrecord.model.validation.ExistingDiagnoseNameValidation;
import com.rewedigital.medicalrecord.repository.DiagnoseRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ExistingDiagnoseNameValidator implements ConstraintValidator<ExistingDiagnoseNameValidation, String> {

    private final DiagnoseRepository diagnoseRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value == null || diagnoseRepository.findByNameAndDeletedFalse(value.toUpperCase()).isPresent();
    }

}
