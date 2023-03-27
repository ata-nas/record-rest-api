package com.rewedigital.medicalrecord.service;

import com.rewedigital.medicalrecord.model.entity.GpEntity;

public interface GpService {

    GpEntity getByUic(String uic);

}
