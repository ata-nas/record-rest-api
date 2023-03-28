package com.rewedigital.medicalrecord.service.impl;

import com.rewedigital.medicalrecord.exception.NoSuchGpEntityFoundException;
import com.rewedigital.medicalrecord.model.entity.GpEntity;
import com.rewedigital.medicalrecord.repository.GpRepository;
import com.rewedigital.medicalrecord.service.GpService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GpServiceImpl implements GpService {
    // TODO Remove ? Combine with DoctorService? Need the repo for Mapper/Annotation Validations, but class needed ?

    private final GpRepository gpRepository;

    @Override
    public GpEntity getByUic(String uic) {
        return gpRepository.findByUic(uic)
                .orElseThrow(() -> new NoSuchGpEntityFoundException("uic", uic));
    }

}
