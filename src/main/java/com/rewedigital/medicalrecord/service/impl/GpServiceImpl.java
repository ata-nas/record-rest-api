package com.rewedigital.medicalrecord.service.impl;

import com.rewedigital.medicalrecord.repository.GpRepository;
import com.rewedigital.medicalrecord.service.GpService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GpServiceImpl implements GpService {

    private final GpRepository gpRepository;

}
