package com.rewedigital.medicalrecord.service;

import com.rewedigital.medicalrecord.model.entity.GpEntity;

public interface GpService { // TODO See TODO in Impl! Remove? Combine with Doctor?

    GpEntity getByUic(String uic);

}
