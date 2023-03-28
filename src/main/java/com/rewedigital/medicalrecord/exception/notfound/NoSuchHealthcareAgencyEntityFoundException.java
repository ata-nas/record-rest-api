package com.rewedigital.medicalrecord.exception.notfound;

import com.rewedigital.medicalrecord.exception.NoSuchEntityFoundException;

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
