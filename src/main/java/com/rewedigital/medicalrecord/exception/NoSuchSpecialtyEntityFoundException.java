package com.rewedigital.medicalrecord.exception;

import com.rewedigital.medicalrecord.exception.base.NoSuchEntityFoundException;

public class NoSuchSpecialtyEntityFoundException extends NoSuchEntityFoundException {

    public NoSuchSpecialtyEntityFoundException() {
        super();
    }

    public NoSuchSpecialtyEntityFoundException(String message) {
        super(message);
    }

    public NoSuchSpecialtyEntityFoundException(String field, String value) {
        super("Specialty", field, value);
    }

}
