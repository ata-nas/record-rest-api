package com.rewedigital.medicalrecord.model.mapper.util;

import com.rewedigital.medicalrecord.exception.NoSuchDiagnoseEntityFoundException;
import com.rewedigital.medicalrecord.exception.NoSuchGpEntityFoundException;
import com.rewedigital.medicalrecord.exception.NoSuchSpecialtyEntityFoundException;
import com.rewedigital.medicalrecord.model.entity.DiagnoseEntity;
import com.rewedigital.medicalrecord.model.entity.GpEntity;
import com.rewedigital.medicalrecord.model.entity.SpecialtyEntity;
import com.rewedigital.medicalrecord.repository.DiagnoseRepository;
import com.rewedigital.medicalrecord.repository.GpRepository;

import com.rewedigital.medicalrecord.repository.SpecialtyRepository;
import lombok.RequiredArgsConstructor;

import org.mapstruct.MapperConfig;
import org.mapstruct.Named;

import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@MapperConfig
@RequiredArgsConstructor
public class MapperUtil {

    private final GpRepository gpRepository;
    private final DiagnoseRepository diagnoseRepository;
    private final SpecialtyRepository specialtyRepository;

    @Named("toUpper")
    public static String toUpper(String s) {
        return s.toUpperCase();
    }

    @Named("findGpByUicCreateUpdate")
    public GpEntity findGpByUicCreateUpdate(String uic) {
        return uic != null ?
                gpRepository.findByUic(uic).orElseThrow(() -> new NoSuchGpEntityFoundException("uic", uic)) : null;
    }

    @Named("findDiagnoseByNameCreateUpdate")
    public DiagnoseEntity findDiagnoseByNameCreateUpdate(String name) {
        return diagnoseRepository.findByName(name)
                .orElseThrow(() -> new NoSuchDiagnoseEntityFoundException("name", name));
    }

    @Named("findAllSpecialtiesByNameCreateUpdate")
    public Set<SpecialtyEntity> findAllSpecialtiesByNameCreateUpdate(Set<String> names) {
        Set<SpecialtyEntity> result = specialtyRepository.findAllByNameIn(names);
        if (result.isEmpty()) {
            throw new NoSuchSpecialtyEntityFoundException("No Specialties by given {name} found!");
        }
        return result;
    }

}
