package com.rewedigital.medicalrecord.exception.notfound;

import com.rewedigital.medicalrecord.exception.NoSuchEntityFoundException;

public class NoSuchSpecialtyEntityFoundException extends NoSuchEntityFoundException {

    public NoSuchSpecialtyEntityFoundException(String message) {
        super(message);
    }

    public NoSuchSpecialtyEntityFoundException(String field, String value) {
        super("Specialty", field, value);
    }

}
