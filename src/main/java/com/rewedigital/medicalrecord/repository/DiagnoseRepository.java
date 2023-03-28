package com.rewedigital.medicalrecord.repository;

import com.rewedigital.medicalrecord.model.dto.diagnose.CreateDiagnoseDTO;
import com.rewedigital.medicalrecord.model.dto.diagnose.DiagnoseDTO;
import com.rewedigital.medicalrecord.model.entity.DiagnoseEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface DiagnoseRepository extends JpaRepository<DiagnoseEntity, Long> {

    Optional<DiagnoseEntity> findByName(String name);

    Set<DiagnoseEntity> findAllByNameIn(Set<String> names);

}
