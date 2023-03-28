package com.rewedigital.medicalrecord.model.validation.validator;

import com.rewedigital.medicalrecord.model.validation.NotExistingAppointmentUicValidation;
import com.rewedigital.medicalrecord.repository.AppointmentRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NotExistingAppointmentUicValidator implements ConstraintValidator<NotExistingAppointmentUicValidation, String> {

    private final AppointmentRepository appointmentRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return appointmentRepository.findByUic(value).isEmpty();
    }

}
