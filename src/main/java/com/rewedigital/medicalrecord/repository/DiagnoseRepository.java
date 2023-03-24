package com.rewedigital.medicalrecord.repository;

import com.rewedigital.medicalrecord.model.entity.DiagnoseEntity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DiagnoseRepository extends JpaRepository<DiagnoseEntity, Long> {

    Optional<DiagnoseEntity> findByName(String name);

}
