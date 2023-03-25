package com.rewedigital.medicalrecord.model.mapper.util;

import com.rewedigital.medicalrecord.exception.NoSuchGpEntityFoundException;
import com.rewedigital.medicalrecord.exception.NoSuchPatientEntityFoundException;
import com.rewedigital.medicalrecord.model.entity.GpEntity;
import com.rewedigital.medicalrecord.repository.GpRepository;

import com.rewedigital.medicalrecord.repository.PatientRepository;
import lombok.RequiredArgsConstructor;

import org.mapstruct.MapperConfig;
import org.mapstruct.Named;

import org.springframework.stereotype.Component;


@Component
@MapperConfig
@RequiredArgsConstructor
public class MapperUtil {

    private final GpRepository gpRepository;
    private final PatientRepository patientRepository;

    @Named("toUpper")
    public static String toUpper(String s) {
        return s.toUpperCase();
    }

    @Named("findGpByUicCreateUpdate")
    public GpEntity findGpByUicCreate(String uic) {
        return uic != null ?
                gpRepository.findByUic(uic).orElseThrow(() -> new NoSuchGpEntityFoundException("uic", uic)) : null;
    }

    @Named("findPatientIdByUic")
    public Long findPatientIdByUic(String uic) {
        return uic != null ?
                patientRepository.findByUic(uic)
                        .orElseThrow(() -> new NoSuchPatientEntityFoundException("uic", uic)).getId() : null;
    }

}
