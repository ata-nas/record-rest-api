package com.rewedigital.medicalrecord.exception.patient;

import com.rewedigital.medicalrecord.exception.base.NoSuchEntityFoundException;

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
