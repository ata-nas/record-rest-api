package com.rewedigital.medicalrecord.model.mapper;

import com.rewedigital.medicalrecord.model.dto.patient.insurance.PatientInsuranceHistoryDTO;
import com.rewedigital.medicalrecord.model.entity.PatientInsuranceHistoryEntity;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PatientInsuranceHistoryMapper {

    PatientInsuranceHistoryEntity toEntity(PatientInsuranceHistoryDTO patientInsuranceHistoryDTO);

}
