package com.rewedigital.medicalrecord.service;

import com.rewedigital.medicalrecord.model.dto.healthcare.HealthcareAgencyDTO;
import com.rewedigital.medicalrecord.model.entity.HealthcareAgencyEntity;

public interface HealthcareAgencyService {

    HealthcareAgencyEntity getByCountry(String country);
    HealthcareAgencyDTO getByCountryToDTO(String country);
    HealthcareAgencyDTO update(String country, HealthcareAgencyDTO healthcareAgencyDTO);

}
