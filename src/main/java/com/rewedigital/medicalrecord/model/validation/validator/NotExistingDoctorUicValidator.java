package com.rewedigital.medicalrecord.model.validation.validator;

import com.rewedigital.medicalrecord.model.validation.NotExistingDoctorUicValidation;
import com.rewedigital.medicalrecord.repository.DoctorRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NotExistingDoctorUicValidator implements ConstraintValidator<NotExistingDoctorUicValidation, String> {

    private final DoctorRepository doctorRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return doctorRepository.findByUic(value).isEmpty();
    }

}
