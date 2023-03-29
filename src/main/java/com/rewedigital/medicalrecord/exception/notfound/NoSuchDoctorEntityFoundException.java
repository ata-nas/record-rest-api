package com.rewedigital.medicalrecord.exception.notfound;

import com.rewedigital.medicalrecord.exception.NoSuchEntityFoundException;

public class NoSuchDoctorEntityFoundException extends NoSuchEntityFoundException {

    public NoSuchDoctorEntityFoundException(String message) {
        super(message);
    }

    public NoSuchDoctorEntityFoundException(String field, String value) {
        super("Doctor", field, value);
    }

}
