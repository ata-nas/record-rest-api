package com.rewedigital.medicalrecord.exception;

import com.rewedigital.medicalrecord.model.dto.exception.GeneralExceptionDTO;

import lombok.Getter;

import org.springframework.http.HttpStatus;

@Getter
public class NoSuchEntityFoundException extends RuntimeException {

    private final GeneralExceptionDTO data;

    public NoSuchEntityFoundException() {
        super();
        data = generateDTO("No such Entity found!");
    }

    public NoSuchEntityFoundException(String message) {
        super(message);
        data = generateDTO(message);
    }

    public NoSuchEntityFoundException(String entity, String field, String value) {
        String message = String.format(
                "%s with field: '%s' and value: '%s' was not found!",
                entity, field, value
                );
        data = generateDTO(message);
    }

    private GeneralExceptionDTO generateDTO(String message) {
        return new GeneralExceptionDTO(
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND,
                message
        );
    }

}
