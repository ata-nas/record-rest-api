package com.rewedigital.medicalrecord.model.mapper;

import com.rewedigital.medicalrecord.model.dto.doctor.CreateDoctorDTO;
import com.rewedigital.medicalrecord.model.dto.doctor.DoctorDTO;
import com.rewedigital.medicalrecord.model.entity.DoctorEntity;
import com.rewedigital.medicalrecord.model.mapper.util.MapperUtil;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = MapperUtil.class)
public interface DoctorMapper {

    DoctorDTO toDTO(DoctorEntity doctorEntity);

    @Mapping(source = "specialtiesNames", target = "specialties", qualifiedByName = "findAllSpecialtiesByNameCreateUpdate")
    DoctorEntity toEntity(CreateDoctorDTO createDoctorDTO);

}
