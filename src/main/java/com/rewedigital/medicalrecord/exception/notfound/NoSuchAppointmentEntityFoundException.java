package com.rewedigital.medicalrecord.exception.notfound;

import com.rewedigital.medicalrecord.exception.NoSuchEntityFoundException;

public class NoSuchAppointmentEntityFoundException extends NoSuchEntityFoundException {

    public NoSuchAppointmentEntityFoundException(String message) {
        super(message);
    }

    public NoSuchAppointmentEntityFoundException(String field, String value) {
        super("Appointment", field, value);
    }

}
