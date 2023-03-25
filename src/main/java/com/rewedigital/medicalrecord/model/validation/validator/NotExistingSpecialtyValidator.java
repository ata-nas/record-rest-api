package com.rewedigital.medicalrecord.model.validation.validator;

import com.rewedigital.medicalrecord.model.validation.NotExistingSpecialtyValidation;
import com.rewedigital.medicalrecord.repository.SpecialtyRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class NotExistingSpecialtyValidator implements ConstraintValidator<NotExistingSpecialtyValidation, String> {

    private final SpecialtyRepository specialtyRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return specialtyRepository.findByName(value).isEmpty();
    }

}
