package com.rewedigital.medicalrecord.exception;

import com.rewedigital.medicalrecord.exception.base.NoSuchEntityFoundException;
import lombok.Getter;

@Getter
public class NoSuchDiagnoseEntityFoundException extends NoSuchEntityFoundException {

    public NoSuchDiagnoseEntityFoundException() {
        super();
    }

    public NoSuchDiagnoseEntityFoundException(String message) {
        super(message);
    }

    public NoSuchDiagnoseEntityFoundException(String field, String value) {
        super("Diagnose", field, value);
    }

}
