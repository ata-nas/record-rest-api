package com.rewedigital.medicalrecord.model.mapper;

import com.rewedigital.medicalrecord.model.dto.healthcare.HealthcareAgencyDTO;
import com.rewedigital.medicalrecord.model.entity.HealthcareAgencyEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HealthcareAgencyMapper {

    HealthcareAgencyDTO toDTO(HealthcareAgencyEntity healthcareAgency);

}
