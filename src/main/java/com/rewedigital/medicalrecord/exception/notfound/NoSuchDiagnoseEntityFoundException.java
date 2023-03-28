package com.rewedigital.medicalrecord.exception.notfound;

import com.rewedigital.medicalrecord.exception.NoSuchEntityFoundException;

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
