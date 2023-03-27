package com.rewedigital.medicalrecord.model.validation.validator;

import com.rewedigital.medicalrecord.model.validation.NotExistingDiagnoseNameValidation;
import com.rewedigital.medicalrecord.repository.DiagnoseRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NotExistingDiagnoseNameValidator implements ConstraintValidator<NotExistingDiagnoseNameValidation, String> {

    private final DiagnoseRepository diagnoseRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return diagnoseRepository.findByName(value).isEmpty();
    }

}
