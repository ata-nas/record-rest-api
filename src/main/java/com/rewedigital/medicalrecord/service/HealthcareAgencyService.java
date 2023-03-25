package com.rewedigital.medicalrecord.service;

import com.rewedigital.medicalrecord.model.dto.healthcare.HealthcareAgencyDTO;
import com.rewedigital.medicalrecord.model.entity.HealthcareAgencyEntity;

public interface HealthcareAgencyService {

    HealthcareAgencyEntity getHealthcareAgency(String country);
    HealthcareAgencyDTO getFees(String country);
    HealthcareAgencyDTO updateFees(String country, HealthcareAgencyDTO healthcareAgencyDTO);

}
