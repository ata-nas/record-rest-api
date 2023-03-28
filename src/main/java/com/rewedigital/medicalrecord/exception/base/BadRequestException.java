package com.rewedigital.medicalrecord.exception.base;

import com.rewedigital.medicalrecord.model.dto.exception.GeneralExceptionDTO;

import lombok.Getter;

import org.springframework.http.HttpStatus;

@Getter
public class BadRequestException extends RuntimeException { // TODO Remove ?

    private final GeneralExceptionDTO data;

    public BadRequestException() {
        super();
        data = new GeneralExceptionDTO(
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND,
                "Bad Request!"
        );
    }

    public BadRequestException(String message) {
        super(message);
        data = new GeneralExceptionDTO(
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND,
                message
        );
    }

}
