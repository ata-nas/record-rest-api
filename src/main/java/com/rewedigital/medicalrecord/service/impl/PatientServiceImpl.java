package com.rewedigital.medicalrecord.service.impl;

import com.rewedigital.medicalrecord.repository.PatientRepository;
import com.rewedigital.medicalrecord.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

}
