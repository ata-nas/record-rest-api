package com.rewedigital.medicalrecord.model.validation.validator;

import com.rewedigital.medicalrecord.model.validation.NotExistingPatientUicValidation;
import com.rewedigital.medicalrecord.repository.PatientRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NotExistingPatientUicValidator implements ConstraintValidator<NotExistingPatientUicValidation, String> {

    private final PatientRepository patientRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return patientRepository.findByUic(value).isEmpty();
    }

}
