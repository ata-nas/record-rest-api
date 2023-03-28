package com.rewedigital.medicalrecord.model.validation.validator;

import com.rewedigital.medicalrecord.model.validation.ExistingAppointmentUicValidation;
import com.rewedigital.medicalrecord.repository.AppointmentRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ExistingAppointmentUicValidator implements ConstraintValidator<ExistingAppointmentUicValidation, String> {

    private final AppointmentRepository appointmentRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value == null || appointmentRepository.findByUic(value.toUpperCase()).isPresent();
    }

}
