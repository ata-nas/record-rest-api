package com.rewedigital.medicalrecord.exception.notfound;

import com.rewedigital.medicalrecord.exception.NoSuchEntityFoundException;

public class NoSuchPatientEntityFoundException extends NoSuchEntityFoundException {

    public NoSuchPatientEntityFoundException() {
        super();
    }

    public NoSuchPatientEntityFoundException(String message) {
        super(message);
    }

    public NoSuchPatientEntityFoundException(String field, String value) {
        super("Patient", field, value);
    }

}
