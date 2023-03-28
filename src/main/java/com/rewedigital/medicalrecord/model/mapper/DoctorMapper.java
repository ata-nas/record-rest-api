package com.rewedigital.medicalrecord.model.mapper;

import com.rewedigital.medicalrecord.model.dto.doctor.CreateDoctorDTO;
import com.rewedigital.medicalrecord.model.dto.doctor.DoctorDTO;
import com.rewedigital.medicalrecord.model.entity.DoctorEntity;
import com.rewedigital.medicalrecord.model.entity.GpEntity;
import com.rewedigital.medicalrecord.model.mapper.util.MapperUtil;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = MapperUtil.class)
public interface DoctorMapper {

    @Mapping(target = "isGp", source = "doctorEntity.id", qualifiedByName = "findGpByIdCheckTrue")
    DoctorDTO toDTO(DoctorEntity doctorEntity);

    @Mapping(source = "specialtiesNames", target = "specialties", qualifiedByName = "findAllSpecialtiesByNameCreateUpdate")
    DoctorEntity toEntity(CreateDoctorDTO createDoctorDTO);

    @Mapping(source = "specialtiesNames", target = "specialties", qualifiedByName = "findAllSpecialtiesByNameCreateUpdate")
    GpEntity toEntityGp(CreateDoctorDTO createDoctorDTO);

    List<DoctorDTO> allToDTO(List<DoctorEntity> doctorEntities);

}
