package com.rewedigital.medicalrecord.repository;

import com.rewedigital.medicalrecord.model.entity.DiagnoseEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface DiagnoseRepository extends JpaRepository<DiagnoseEntity, Long> {

    Optional<DiagnoseEntity> findByName(String name);

    Optional<DiagnoseEntity> findByNameAndDeletedFalse(String name);

    @Query(
            "SELECT e FROM DiagnoseEntity e " +
                    "WHERE e.deleted = false"
    )
    List<DiagnoseEntity> findAllDeletedFalse();

    Set<DiagnoseEntity> findAllByNameIn(Set<String> names);

    Set<DiagnoseEntity> findAllByNameInAndDeletedFalse(Set<String> names);

    @Query("UPDATE DiagnoseEntity e SET e.deleted = true WHERE e.name = :name")
    @Modifying
    void softDelete(String name);

    @Query("UPDATE DiagnoseEntity e SET e.deleted = false WHERE e.name = :name")
    @Modifying
    void softCreate(String name);

}
