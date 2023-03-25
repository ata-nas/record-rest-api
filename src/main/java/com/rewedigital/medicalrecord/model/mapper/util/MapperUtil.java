package com.rewedigital.medicalrecord.model.mapper.util;

import com.rewedigital.medicalrecord.model.entity.GpEntity;
import com.rewedigital.medicalrecord.repository.GpRepository;

import lombok.RequiredArgsConstructor;

import org.mapstruct.MapperConfig;
import org.mapstruct.Named;

import org.springframework.stereotype.Component;


@Component
@MapperConfig
@RequiredArgsConstructor
public class MapperUtil {

    private final GpRepository gpRepository;

    @Named("toUpper")
    public static String toUpper(String s) {
        return s.toUpperCase();
    }

    @Named("findGpByUicCreate")
    public GpEntity findGpByUicCreate(String uic) {
        return uic != null ? gpRepository.findByUic(uic).orElse(null) : null;
    }

}
