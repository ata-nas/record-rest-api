package com.rewedigital.medicalrecord.model.validation.validator;

import com.rewedigital.medicalrecord.model.validation.ExistingDoctorUicValidation;
import com.rewedigital.medicalrecord.repository.DoctorRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ExistingDoctorUicValidator implements ConstraintValidator<ExistingDoctorUicValidation, String> {

    private final DoctorRepository doctorRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value == null || doctorRepository.findByUic(value).isPresent();
    }

}
