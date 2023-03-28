package com.rewedigital.medicalrecord.service.impl;

import com.rewedigital.medicalrecord.exception.NoSuchHealthcareAgencyEntityFoundException;
import com.rewedigital.medicalrecord.model.dto.healthcare.HealthcareAgencyDTO;
import com.rewedigital.medicalrecord.model.entity.HealthcareAgencyEntity;
import com.rewedigital.medicalrecord.model.mapper.HealthcareAgencyMapper;
import com.rewedigital.medicalrecord.repository.HealthcareAgencyRepository;
import com.rewedigital.medicalrecord.service.HealthcareAgencyService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HealthcareAgencyServiceImpl implements HealthcareAgencyService {

    private final HealthcareAgencyRepository healthcareAgencyRepository;
    private final HealthcareAgencyMapper healthcareAgencyMapper;

    @Override
    public HealthcareAgencyEntity getByCountry(String country) {
        return healthcareAgencyRepository.findByCountry(country)
                .orElseThrow(() -> new NoSuchHealthcareAgencyEntityFoundException("country", country.trim().toUpperCase()));
    }

    @Override
    public HealthcareAgencyDTO getByCountryToDTO(String country) {
        return healthcareAgencyMapper.toDTO(getByCountry(country));
    }

    @Override
    public HealthcareAgencyDTO update(String country, HealthcareAgencyDTO healthcareAgencyDTO) {
        return healthcareAgencyMapper.toDTO(
                healthcareAgencyRepository.save(healthcareAgencyMapper.update(healthcareAgencyDTO, getByCountry(country)))
        );
    }

}
