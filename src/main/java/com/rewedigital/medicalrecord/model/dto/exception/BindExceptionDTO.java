package com.rewedigital.medicalrecord.model.dto.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class BindExceptionDTO {

    private final LocalDateTime timestamp = LocalDateTime.now();
    private final Integer status;
    private final HttpStatus error;
    private final List<BindExceptionPropertiesDTO> errors;

}
