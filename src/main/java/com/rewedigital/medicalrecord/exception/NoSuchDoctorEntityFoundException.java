package com.rewedigital.medicalrecord.exception;

import com.rewedigital.medicalrecord.exception.base.NoSuchEntityFoundException;

public class NoSuchDoctorEntityFoundException extends NoSuchEntityFoundException {

    public NoSuchDoctorEntityFoundException() {
        super();
    }

    public NoSuchDoctorEntityFoundException(String message) {
        super(message);
    }

    public NoSuchDoctorEntityFoundException(String field, String value) {
        super("Doctor", field, value);
    }

}
