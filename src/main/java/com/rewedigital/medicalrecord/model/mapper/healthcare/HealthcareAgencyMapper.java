package com.rewedigital.medicalrecord.model.mapper.healthcare;

import com.rewedigital.medicalrecord.model.dto.healthcare.HealthcareAgencyDTO;
import com.rewedigital.medicalrecord.model.entity.HealthcareAgencyEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HealthcareAgencyMapper {

    HealthcareAgencyDTO healthcareAgencyToHealthcareAgencyDTO(HealthcareAgencyEntity healthcareAgency);
    HealthcareAgencyEntity healthcareAgencyDTOToHealthcareAgencyEntity(HealthcareAgencyDTO healthcareAgencyDTO);

}
