package com.rewedigital.medicalrecord.service.impl;

import com.rewedigital.medicalrecord.repository.DoctorRepository;
import com.rewedigital.medicalrecord.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;

}
