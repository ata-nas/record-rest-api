package com.rewedigital.medicalrecord.model.mapper;

import com.rewedigital.medicalrecord.model.dto.exception.BindExceptionPropertiesDTO;

import org.mapstruct.Mapper;

import org.springframework.validation.FieldError;

@Mapper(componentModel = "spring")
public interface FieldErrorMapper {

    BindExceptionPropertiesDTO toDTO(FieldError objectError);

}
