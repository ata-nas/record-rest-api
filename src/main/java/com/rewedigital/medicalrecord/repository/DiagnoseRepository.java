package com.rewedigital.medicalrecord.repository;

import com.rewedigital.medicalrecord.model.entity.DiagnoseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiagnoseRepository extends JpaRepository<DiagnoseEntity, Long> {
}
