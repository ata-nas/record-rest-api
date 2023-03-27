package com.rewedigital.medicalrecord.exception;

import com.rewedigital.medicalrecord.exception.base.NoSuchEntityFoundException;

public class NoSuchHealthcareAgencyEntityFoundException extends NoSuchEntityFoundException {

    public NoSuchHealthcareAgencyEntityFoundException(String field, String value) {
        super("HealthcareAgency", field, value);
    }

}
