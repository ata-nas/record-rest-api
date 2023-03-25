package com.rewedigital.medicalrecord.model.validation.validator;

import com.rewedigital.medicalrecord.model.validation.UniquePatientValidation;
import com.rewedigital.medicalrecord.repository.PatientRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UniquePatientValidator implements ConstraintValidator<UniquePatientValidation, String> {

    private final PatientRepository patientRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return patientRepository.findByUic(value).isEmpty();
    }

}
