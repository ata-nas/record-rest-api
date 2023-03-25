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

    private final GpRepository gpRepository;

    @Override
    public GpEntity findByUic(String uic) {
        return gpRepository.findByUic(uic)
                .orElseThrow(() -> new NoSuchGpEntityFoundException("uic", uic));
    }
}
