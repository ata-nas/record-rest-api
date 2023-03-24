package com.rewedigital.medicalrecord.exception.base;

import com.rewedigital.medicalrecord.model.dto.exception.GeneralExceptionDTO;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class NoSuchEntityFoundException extends RuntimeException {

    private final GeneralExceptionDTO data;


    public NoSuchEntityFoundException() {
        super();
        data = new GeneralExceptionDTO(
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND,
                "No such Entity found!"
        );
    }

    public NoSuchEntityFoundException(String message) {
        super(message);
        data = new GeneralExceptionDTO(
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND,
                message
        );

    }

}
