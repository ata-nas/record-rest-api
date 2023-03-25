package com.rewedigital.medicalrecord.exception;

import com.rewedigital.medicalrecord.exception.base.NoSuchEntityFoundException;

public class NoSuchHealthcareAgencyEntityFoundException extends NoSuchEntityFoundException {

    public NoSuchHealthcareAgencyEntityFoundException() {
        super();
    }

    public NoSuchHealthcareAgencyEntityFoundException(String message) {
        super(message);
    }

    public NoSuchHealthcareAgencyEntityFoundException(String field, String value) {
        super("HealthcareAgency", field, value);
    }

}
