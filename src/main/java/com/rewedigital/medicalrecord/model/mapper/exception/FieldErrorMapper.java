package com.rewedigital.medicalrecord.model.mapper.exception;

import com.rewedigital.medicalrecord.model.dto.exception.bind.BindExceptionPropertiesDTO;

import org.mapstruct.Mapper;

import org.springframework.validation.FieldError;

@Mapper(componentModel = "spring")
public interface FieldErrorMapper {

    BindExceptionPropertiesDTO fieldErrorToBindExceptionPropertiesDTO(FieldError objectError);

}
