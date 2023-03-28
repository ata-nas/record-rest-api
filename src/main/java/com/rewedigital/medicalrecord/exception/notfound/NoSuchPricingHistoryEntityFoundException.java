package com.rewedigital.medicalrecord.exception.notfound;

import com.rewedigital.medicalrecord.exception.NoSuchEntityFoundException;

public class NoSuchPricingHistoryEntityFoundException extends NoSuchEntityFoundException {

    public NoSuchPricingHistoryEntityFoundException() {
        super();
    }

    public NoSuchPricingHistoryEntityFoundException(String message) {
        super(message);
    }

    public NoSuchPricingHistoryEntityFoundException(String field, String value) {
        super("Pricing History", field, value);
    }
}
