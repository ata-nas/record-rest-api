package com.rewedigital.medicalrecord.model.validation.validator;

import com.rewedigital.medicalrecord.model.validation.ExistingPatientUicValidation;
import com.rewedigital.medicalrecord.repository.PatientRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ExistingPatientUicValidator implements ConstraintValidator<ExistingPatientUicValidation, String> {

    private final PatientRepository patientRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value == null || patientRepository.findByUicAndDeletedFalse(value).isPresent();
    }

}
