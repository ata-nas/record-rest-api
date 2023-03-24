package com.rewedigital.medicalrecord.model.mapper.specialty;

import com.rewedigital.medicalrecord.model.dto.specialty.CreateSpecialtyDTO;
import com.rewedigital.medicalrecord.model.dto.specialty.SpecialtyDTO;
import com.rewedigital.medicalrecord.model.entity.SpecialtyEntity;
import com.rewedigital.medicalrecord.model.mapper.util.MapperUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = MapperUtil.class)
public interface SpecialtyMapper {

    @Mapping(source = "name", target = "name", qualifiedByName = "toUpper")
    SpecialtyDTO specialtyEntityToSpecialtyDTO(SpecialtyEntity specialtyEntity);

    @Mapping(source = "name", target = "name", qualifiedByName = "toUpper")
    SpecialtyEntity specialtyDTOToSpecialtyEntity(SpecialtyDTO specialtyDTO);

    List<SpecialtyDTO> allSpecialtyEntityToSpecialtyDTO(List<SpecialtyEntity> specialtyEntity);

    List<SpecialtyEntity> allSpecialtyDTOToSpecialtyEntity(List<SpecialtyDTO> specialtyDTOS);

    @Mapping(source = "name", target = "name", qualifiedByName = "toUpper")
    CreateSpecialtyDTO specialtyEntityToCreateSpecialtyDTO(SpecialtyEntity specialtyEntity);

    @Mapping(source = "name", target = "name", qualifiedByName = "toUpper")
    SpecialtyEntity createSpecialtyDTOToSpecialtyEntity(CreateSpecialtyDTO createSpecialtyDTO);

    List<CreateSpecialtyDTO> allSpecialtyEntityToCreateSpecialtyDTO(List<SpecialtyEntity> specialtyEntity);

    List<SpecialtyEntity> allCreateSpecialtyDTOToSpecialtyEntity(List<CreateSpecialtyDTO> createSpecialtyDTOS);

}
