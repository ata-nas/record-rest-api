package com.rewedigital.medicalrecord.model.mapper;

import com.rewedigital.medicalrecord.model.dto.patient.insurance.PatientInsuranceHistoryDTO;
import com.rewedigital.medicalrecord.model.entity.PatientInsuranceHistoryEntity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PatientInsuranceHistoryMapper {

    @Mapping(target = "id", ignore = true)
    PatientInsuranceHistoryEntity toEntity(PatientInsuranceHistoryDTO patientInsuranceHistoryDTO);

}
