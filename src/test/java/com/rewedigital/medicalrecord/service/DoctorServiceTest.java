package com.rewedigital.medicalrecord.service;

import com.rewedigital.medicalrecord.exception.notfound.NoSuchDoctorEntityFoundException;
import com.rewedigital.medicalrecord.model.dto.doctor.CreateDoctorDTO;
import com.rewedigital.medicalrecord.model.dto.doctor.DoctorDTO;
import com.rewedigital.medicalrecord.model.dto.doctor.UpdateDoctorDTO;
import com.rewedigital.medicalrecord.model.dto.stats.DoctorIncomeDTO;
import com.rewedigital.medicalrecord.model.entity.DoctorEntity;
import com.rewedigital.medicalrecord.model.mapper.DoctorMapper;
import com.rewedigital.medicalrecord.repository.DoctorRepository;
import com.rewedigital.medicalrecord.repository.GpRepository;
import com.rewedigital.medicalrecord.service.impl.DoctorServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

@ExtendWith(MockitoExtension.class)
class DoctorServiceTest {

    @Mock
    private DoctorRepository doctorRepository;

    @Mock
    private DoctorMapper doctorMapper;

    @Mock
    private GpRepository gpRepository;

    @InjectMocks
    private DoctorServiceImpl toTest;

    @BeforeEach
    public void init() {
        openMocks(doctorRepository);
        openMocks(doctorMapper);
    }

    @Test
    public void testGetByUic_ReturnsDoctorByUic() {
        DoctorEntity expected = new DoctorEntity();
        ReflectionTestUtils.setField(expected, "uic", "1");

        when(doctorRepository.findByUicAndDeletedFalse("1"))
                .thenReturn(Optional.of(expected));

        DoctorEntity actual = toTest.getByUic("1");

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testGetByUic_ThrowsNoSuchDoctorEntityFoundExceptionIfEntityNotFound() {
        assertThatExceptionOfType(NoSuchDoctorEntityFoundException.class)
                .isThrownBy(() -> toTest.getByUic("wrong value"));
    }

    @Test
    public void testGetByUicToDTO_ReturnsDoctorDTO() {
        DoctorEntity expected = new DoctorEntity();
        ReflectionTestUtils.setField(expected, "uic", "1");

        DoctorDTO doctorDTO = new DoctorDTO();
        ReflectionTestUtils.setField(doctorDTO, "uic", "1");

        when(doctorRepository.findByUicAndDeletedFalse("1"))
                .thenReturn(Optional.of(expected));

        when(doctorMapper.toDTO(doctorRepository.findByUicAndDeletedFalse("1").get()))
                .thenReturn(doctorDTO);

        DoctorDTO actual = toTest.getByUicToDTO("1");

        assertThat(actual.getUic()).isEqualTo(expected.getUic());
    }

    @Test
    public void testGetByUicToDTO_ThrowsNoSuchDoctorEntityFoundExceptionIfEntityNotFound() {
        assertThatExceptionOfType(NoSuchDoctorEntityFoundException.class)
                .isThrownBy(() -> toTest.getByUicToDTO("wrong value"));
    }

    @Test
    public void testGetAll_ReturnsCollectionWithAllDoctors() {
        when(doctorRepository.findAllByDeletedFalse())
                .thenReturn(Set.of(new DoctorEntity()));

        Set<DoctorEntity> actual = toTest.getAll();

        assertThatCollection(actual).isNotNull();
        assertThatCollection(actual).isNotEmpty();
        assertThatCollection(actual).doesNotContainNull();
        assertThatCollection(actual).hasSize(1);
        assertThat(actual.iterator().next()).isExactlyInstanceOf(DoctorEntity.class);
    }

    @Test
    public void testGetAll_ThrowsNoSuchDoctorEntityFoundExceptionIfEntityNotFound() {
        assertThatExceptionOfType(NoSuchDoctorEntityFoundException.class)
                .isThrownBy(() -> toTest.getAll());
    }

    @Test
    public void testGetAllToDTO_ReturnsCollectionWithAllDoctorsDTO() {
        when(doctorRepository.findAllByDeletedFalse())
                .thenReturn(Set.of(new DoctorEntity()));

        when(doctorMapper.allToDTO(doctorRepository.findAllByDeletedFalse()))
                .thenReturn(Set.of(new DoctorDTO()));

        Set<DoctorDTO> actual = toTest.getAllToDTO();

        assertThatCollection(actual).isNotNull();
        assertThatCollection(actual).isNotEmpty();
        assertThatCollection(actual).doesNotContainNull();
        assertThatCollection(actual).hasSize(1);
        assertThat(actual.iterator().next()).isExactlyInstanceOf(DoctorDTO.class);
    }

    @Test
    public void testGetAllToDTO_ThrowsNoSuchDoctorEntityFoundExceptionIfEntityNotFound() {
        assertThatExceptionOfType(NoSuchDoctorEntityFoundException.class)
                .isThrownBy(() -> toTest.getAllToDTO());
    }

    @Test
    public void testCreate_ReturnsPatientDTOOfCreatedPatient() {
        CreateDoctorDTO createDoctorDTO = new CreateDoctorDTO();
        ReflectionTestUtils.setField(createDoctorDTO, "uic", "1");

        DoctorEntity doctorEntity = new DoctorEntity();
        ReflectionTestUtils.setField(doctorEntity, "uic", "1");

        DoctorDTO doctorDTO = new DoctorDTO();
        ReflectionTestUtils.setField(doctorDTO, "uic", "1");

        when(doctorMapper.toEntity(createDoctorDTO))
                .thenReturn(doctorEntity);

        when(doctorRepository.save(doctorMapper.toEntity(createDoctorDTO)))
                .then(AdditionalAnswers.returnsFirstArg());

        when(doctorMapper.toDTO(doctorRepository.save(doctorMapper.toEntity(createDoctorDTO))))
                .thenReturn(doctorDTO);

        DoctorDTO actual = toTest.create(createDoctorDTO);

        assertThat(actual.getUic()).isEqualTo("1");
        assertThat(actual).isExactlyInstanceOf(DoctorDTO.class);
    }

    @Test
    public void testUpdate_ReturnsPatientDTOOfUpdatedPatient() {
        UpdateDoctorDTO updateDoctorDTO = new UpdateDoctorDTO();
        ReflectionTestUtils.setField(updateDoctorDTO, "name", "test");

        DoctorEntity doctorEntity = new DoctorEntity();
        ReflectionTestUtils.setField(doctorEntity, "name", "test");

        DoctorDTO doctorDTO = new DoctorDTO();
        ReflectionTestUtils.setField(doctorDTO, "name", "test");

        when(gpRepository.findByUicAndDeletedFalse("1"))
                .thenReturn(Optional.empty());

        when(doctorRepository.findByUicAndDeletedFalse("1"))
                .thenReturn(Optional.of(doctorEntity));

        when(doctorMapper.toEntity(updateDoctorDTO, doctorRepository.findByUicAndDeletedFalse("1").get()))
                .thenReturn(doctorEntity);

        when(doctorRepository.save(doctorMapper.toEntity(updateDoctorDTO, doctorRepository.findByUicAndDeletedFalse("1").get())))
                .then(AdditionalAnswers.returnsFirstArg());

        when(doctorMapper.toDTO(doctorRepository.save(doctorMapper.toEntity(updateDoctorDTO, doctorRepository.findByUicAndDeletedFalse("1").get()))))
                .thenReturn(doctorDTO);

        DoctorDTO actual = toTest.update("1", updateDoctorDTO);

        assertThat(actual.getName()).isEqualTo("test");
        assertThat(actual).isExactlyInstanceOf(DoctorDTO.class);
    }

    @Test
    public void testUpdate_ThrowsNoSuchDoctorEntityFoundExceptionIfEntityNotFound() {
        assertThatExceptionOfType(NoSuchDoctorEntityFoundException.class)
                .isThrownBy(() -> toTest.update("wrong value", new UpdateDoctorDTO()));
    }

    @Test
    public void testGetDoctorIncomeByUicFromInsuredPatients_ReturnsTheIncomeOfADoctorWithUicFromPatientsWhoWereInsuredAtTimeOfVisit() {
        DoctorIncomeDTO doctorIncomeDTO = new DoctorIncomeDTO();
        ReflectionTestUtils.setField(doctorIncomeDTO, "income", BigDecimal.TEN);

        when(doctorRepository.totalIncomeFromInsured("1"))
                .thenReturn(BigDecimal.TEN);

        DoctorIncomeDTO actual = toTest.getDoctorIncomeByUicFromInsuredPatients("1");

        assertThat(actual).isNull();
    }

    @Test
    public void testGetDoctorIncomeByUicFromNotInsuredPatients_ReturnsTheIncomeOfADoctorWithUicFromPatientsWhoWereNotInsuredAtTimeOfVisit() {
        DoctorIncomeDTO doctorIncomeDTO = new DoctorIncomeDTO();
        ReflectionTestUtils.setField(doctorIncomeDTO, "income", BigDecimal.TEN);

        when(doctorRepository.totalIncomeFromNotInsured("1"))
                .thenReturn(BigDecimal.TEN);

        DoctorIncomeDTO actual = toTest.getDoctorIncomeByUicFromNotInsuredPatients("1");

        assertThat(actual).isNull();
    }

}
