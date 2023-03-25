package com.rewedigital.medicalrecord.service.impl;

import com.rewedigital.medicalrecord.exception.healthcare.NoSuchHealthcareAgencyEntityFoundException;
import com.rewedigital.medicalrecord.model.dto.healthcare.HealthcareAgencyDTO;
import com.rewedigital.medicalrecord.model.entity.HealthcareAgencyEntity;
import com.rewedigital.medicalrecord.model.mapper.healthcare.HealthcareAgencyMapper;
import com.rewedigital.medicalrecord.repository.HealthcareAgencyRepository;
import com.rewedigital.medicalrecord.service.HealthcareAgencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class HealthcareAgencyServiceImpl implements HealthcareAgencyService {

    private final HealthcareAgencyRepository healthcareAgencyRepository;
    private final HealthcareAgencyMapper healthcareAgencyMapper;

    @Override
    public HealthcareAgencyEntity getHealthcareAgency(String country) {
        return healthcareAgencyRepository.findByCountry(country)
                .orElseThrow(() -> new NoSuchHealthcareAgencyEntityFoundException("country", country.trim().toUpperCase()));
    }

    @Override
    public HealthcareAgencyDTO getFees(String country) {
        return healthcareAgencyMapper.healthcareAgencyToHealthcareAgencyDTO(getHealthcareAgency(country));
    }

    @Override
    public HealthcareAgencyDTO updateFees(String country, HealthcareAgencyDTO healthcareAgencyDTO) {
        return healthcareAgencyMapper.healthcareAgencyToHealthcareAgencyDTO(
                healthcareAgencyRepository.save(
                        getHealthcareAgency(country).setAppointmentFees(healthcareAgencyDTO.getAppointmentFees())
                )
        );
    }

}
