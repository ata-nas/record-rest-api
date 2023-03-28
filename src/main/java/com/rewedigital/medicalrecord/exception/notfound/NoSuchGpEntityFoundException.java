package com.rewedigital.medicalrecord.exception.notfound;

import com.rewedigital.medicalrecord.exception.NoSuchEntityFoundException;

public class NoSuchGpEntityFoundException extends NoSuchEntityFoundException {

    public NoSuchGpEntityFoundException() {
        super();
    }

    public NoSuchGpEntityFoundException(String message) {
        super(message);
    }

    public NoSuchGpEntityFoundException(String field, String value) {
        super("Gp", field, value);
    }

}
