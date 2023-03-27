package com.rewedigital.medicalrecord.model.validation.tmp;

import com.rewedigital.medicalrecord.repository.PatientInsuranceHistoryRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class NotOverlappingInsuranceDateValidator implements ConstraintValidator<NotOverlappingInsuranceDateValidation, LocalDateTime> {

    private final PatientInsuranceHistoryRepository patientInsuranceHistoryRepository;

    @Override
    public boolean isValid(LocalDateTime value, ConstraintValidatorContext context) {
        return value == null || patientInsuranceHistoryRepository.findOverlappingStartDate(value).isEmpty();
    }

}
