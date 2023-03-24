package com.rewedigital.medicalrecord.exception.handler;

import com.rewedigital.medicalrecord.exception.base.BadRequestException;
import com.rewedigital.medicalrecord.exception.base.NoSuchEntityFoundException;
import com.rewedigital.medicalrecord.model.dto.exception.bind.BindExceptionDTO;
import com.rewedigital.medicalrecord.model.dto.exception.GeneralExceptionDTO;
import com.rewedigital.medicalrecord.model.mapper.exception.FieldErrorMapper;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public class AppExceptionHandler {

    private final FieldErrorMapper objectErrorMapper;

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    private GeneralExceptionDTO handleExceptionFallback(Exception e) {
        return new GeneralExceptionDTO(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                "INTERNAL SERVER ERROR!"
        );
   }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchEntityFoundException.class)
    private GeneralExceptionDTO handleExceptionNotFound(NoSuchEntityFoundException e) {
        return e.getData();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    private GeneralExceptionDTO handleExceptionBadRequest(BadRequestException e) {
        return e.getData();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    private BindExceptionDTO handleExceptionBind(BindException e) {
        return new BindExceptionDTO(
                400,
                HttpStatus.BAD_REQUEST,
                e.getFieldErrors()
                        .stream()
                        .map(objectErrorMapper::fieldErrorToBindExceptionPropertiesDTO)
                        .toList()
        );
    }

}
