package com.rewedigital.medicalrecord.service;

import com.rewedigital.medicalrecord.exception.notfound.NoSuchPatientEntityFoundException;
import com.rewedigital.medicalrecord.model.dto.patient.CreatePatientDTO;
import com.rewedigital.medicalrecord.model.dto.patient.PatientDTO;
import com.rewedigital.medicalrecord.model.dto.patient.UpdatePatientDTO;
import com.rewedigital.medicalrecord.model.entity.AppointmentEntity;
import com.rewedigital.medicalrecord.model.entity.PatientEntity;
import com.rewedigital.medicalrecord.model.mapper.PatientMapper;
import com.rewedigital.medicalrecord.repository.PatientRepository;
import com.rewedigital.medicalrecord.service.impl.AppointmentServiceImpl;
import com.rewedigital.medicalrecord.service.impl.PatientServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

@ExtendWith(MockitoExtension.class)
class PatientServiceTest {

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private PatientMapper patientMapper;

    @InjectMocks
    private PatientServiceImpl toTest;

    @BeforeEach
    public void init() {
        openMocks(patientRepository);
        openMocks(patientMapper);
    }

    @Test
    public void testGetByUic_ReturnsPatientByUic() {
        PatientEntity expected = new PatientEntity();
        ReflectionTestUtils.setField(expected, "uic", "1");

        when(patientRepository.findByUicAndDeletedFalse("1"))
                .thenReturn(Optional.of(expected));

        PatientEntity actual = toTest.getByUic("1");

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testGetByUic_ThrowsNoSuchPatientEntityFoundExceptionIfEntityNotFound() {
        assertThatExceptionOfType(NoSuchPatientEntityFoundException.class)
                .isThrownBy(() -> toTest.getByUic("wrong value"));
    }

    @Test
    public void testGetByUicToDTO_ReturnsPatientDTO() {
        PatientEntity expected = new PatientEntity();
        ReflectionTestUtils.setField(expected, "uic", "1");

        PatientDTO patientDTO = new PatientDTO();
        ReflectionTestUtils.setField(patientDTO, "uic", "1");

        when(patientRepository.findByUicAndDeletedFalse("1"))
                .thenReturn(Optional.of(expected));

        when(patientMapper.toDTO(patientRepository.findByUicAndDeletedFalse("1").get()))
                .thenReturn(patientDTO);

        PatientDTO actual = toTest.getByUicToDTO("1");

        assertThat(actual.getUic()).isEqualTo(expected.getUic());
    }

    @Test
    public void testGetByUicToDTO_ThrowsNoSuchPatientEntityFoundExceptionIfEntityNotFound() {
        assertThatExceptionOfType(NoSuchPatientEntityFoundException.class)
                .isThrownBy(() -> toTest.getByUicToDTO("wrong value"));
    }
    @Test
    public void testGetAll_ReturnsCollectionWithAllPatient() {
        when(patientRepository.findAllByDeletedFalse())
                .thenReturn(Set.of(new PatientEntity()));

        Set<PatientEntity> actual = toTest.getAll();

        assertThatCollection(actual).isNotNull();
        assertThatCollection(actual).isNotEmpty();
        assertThatCollection(actual).doesNotContainNull();
        assertThatCollection(actual).hasSize(1);
        assertThat(actual.iterator().next()).isExactlyInstanceOf(PatientEntity.class);
    }

    @Test
    public void testGetAll_ThrowsNoSuchPatientEntityFoundExceptionIfEntityNotFound() {
        assertThatExceptionOfType(NoSuchPatientEntityFoundException.class)
                .isThrownBy(() -> toTest.getAll());
    }

    @Test
    public void testGetAllToDTO_ReturnsCollectionWithAllPatientsDTO() {
        when(patientRepository.findAllByDeletedFalse())
                .thenReturn(Set.of(new PatientEntity()));

        when(patientMapper.allToDTO(patientRepository.findAllByDeletedFalse()))
                .thenReturn(Set.of(new PatientDTO()));

        Set<PatientDTO> actual = toTest.getAllToDTO();

        assertThatCollection(actual).isNotNull();
        assertThatCollection(actual).isNotEmpty();
        assertThatCollection(actual).doesNotContainNull();
        assertThatCollection(actual).hasSize(1);
        assertThat(actual.iterator().next()).isExactlyInstanceOf(PatientDTO.class);
    }

    @Test
    public void testGetAllToDTO_ThrowsNoSuchPatientEntityFoundExceptionIfEntityNotFound() {
        assertThatExceptionOfType(NoSuchPatientEntityFoundException.class)
                .isThrownBy(() -> toTest.getAllToDTO());
    }

    @Test
    public void testCreate_ReturnsPatientDTOOfCreatedPatient() {
        CreatePatientDTO createPatientDTO = new CreatePatientDTO();
        ReflectionTestUtils.setField(createPatientDTO, "uic", "1");

        PatientEntity patientEntity = new PatientEntity();
        ReflectionTestUtils.setField(patientEntity, "uic", "1");

        PatientDTO patientDTO = new PatientDTO();
        ReflectionTestUtils.setField(patientDTO, "uic", "1");

        when(patientMapper.toEntity(createPatientDTO))
                .thenReturn(patientEntity);

        when(patientRepository.save(patientMapper.toEntity(createPatientDTO)))
                .then(AdditionalAnswers.returnsFirstArg());

        when(patientMapper.toDTO(patientRepository.save(patientMapper.toEntity(createPatientDTO))))
                .thenReturn(patientDTO);

        PatientDTO actual = toTest.create(createPatientDTO);

        assertThat(actual.getUic()).isEqualTo("1");
        assertThat(actual).isExactlyInstanceOf(PatientDTO.class);
    }

    @Test
    public void testUpdate_ReturnsPatientDTOOfUpdatedPatient() {
        UpdatePatientDTO updatePatientDTO = new UpdatePatientDTO();
        ReflectionTestUtils.setField(updatePatientDTO, "name", "test");

        PatientEntity patientEntity = new PatientEntity();
        ReflectionTestUtils.setField(patientEntity, "name", "test");

        PatientDTO patientDTO = new PatientDTO();
        ReflectionTestUtils.setField(patientDTO, "name", "test");

        when(patientRepository.findByUicAndDeletedFalse("1"))
                .thenReturn(Optional.of(patientEntity));

        when(patientMapper.toEntity(updatePatientDTO, patientRepository.findByUicAndDeletedFalse("1").get()))
                .thenReturn(patientEntity);

        when(patientRepository.save(patientMapper.toEntity(updatePatientDTO, patientRepository.findByUicAndDeletedFalse("1").get())))
                .then(AdditionalAnswers.returnsFirstArg());

        when(patientMapper.toDTO(patientRepository.save(patientMapper.toEntity(updatePatientDTO, patientRepository.findByUicAndDeletedFalse("1").get()))))
                .thenReturn(patientDTO);

        PatientDTO actual = toTest.update("1", updatePatientDTO);

        assertThat(actual.getName()).isEqualTo("test");
        assertThat(actual).isExactlyInstanceOf(PatientDTO.class);
    }

    @Test
    public void testUpdate_ThrowsNoSuchPatientEntityFoundExceptionIfEntityNotFound() {
        assertThatExceptionOfType(NoSuchPatientEntityFoundException.class)
                .isThrownBy(() -> toTest.update("wrong value", new UpdatePatientDTO()));
    }



}
