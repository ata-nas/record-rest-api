package com.rewedigital.medicalrecord.service;

import com.rewedigital.medicalrecord.exception.notfound.NoSuchDiagnoseEntityFoundException;
import com.rewedigital.medicalrecord.model.dto.diagnose.CreateDiagnoseDTO;
import com.rewedigital.medicalrecord.model.dto.diagnose.DiagnoseDTO;
import com.rewedigital.medicalrecord.model.dto.stats.DiagnoseIncomeDTO;
import com.rewedigital.medicalrecord.model.dto.stats.DiagnoseVisitDTO;
import com.rewedigital.medicalrecord.model.entity.DiagnoseEntity;
import com.rewedigital.medicalrecord.model.mapper.DiagnoseMapper;
import com.rewedigital.medicalrecord.repository.DiagnoseRepository;

import com.rewedigital.medicalrecord.service.impl.DiagnoseServiceImpl;

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
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

@ExtendWith(MockitoExtension.class)
class DiagnoseServiceTest {


    @Mock
    private DiagnoseRepository diagnoseRepository;
    @Mock
    private DiagnoseMapper diagnoseMapper;

    @InjectMocks
    private DiagnoseServiceImpl toTest;

    @BeforeEach
    public void init() {
        openMocks(diagnoseRepository);
        openMocks(diagnoseMapper);
    }

    @Test
    public void testGetByName_ReturnsDiagnoseByName() {
        DiagnoseEntity expected = new DiagnoseEntity();
        ReflectionTestUtils.setField(expected,"name", "FLU");

        when(diagnoseRepository.findByNameAndDeletedFalse("FLU"))
                .thenReturn(Optional.of(expected));

        DiagnoseEntity actual = toTest.getByName("FLU");

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testGetByName_ThrowsNoSuchDiagnoseEntityFoundExceptionIfEntityNotFound() {
        assertThatExceptionOfType(NoSuchDiagnoseEntityFoundException.class)
                .isThrownBy(() -> toTest.getByName("wrong value"));
    }

    @Test
    public void testGetByNameToDTO_ReturnsDiagnoseDTO() {
        DiagnoseEntity expected = new DiagnoseEntity();
        ReflectionTestUtils.setField(expected,"name", "FLU");

        DiagnoseDTO diagnoseDTO = new DiagnoseDTO();
        ReflectionTestUtils.setField(diagnoseDTO,"name", "FLU");

        when(diagnoseRepository.findByNameAndDeletedFalse("FLU"))
                .thenReturn(Optional.of(expected));

        when(diagnoseMapper.toDTO(diagnoseRepository.findByNameAndDeletedFalse("FLU").get()))
                .thenReturn(diagnoseDTO);

        DiagnoseDTO actual = toTest.getByNameToDTO("FLU");

        assertThat(actual.getName()).isEqualTo(expected.getName());
    }

    @Test
    public void testGetByNameToDTO_ThrowsNoSuchDiagnoseEntityFoundExceptionIfEntityNotFound() {
        assertThatExceptionOfType(NoSuchDiagnoseEntityFoundException.class)
                .isThrownBy(() -> toTest.getByNameToDTO("wrong value"));
    }

    @Test
    public void testGetAll_ReturnsCollectionWithAllDiagnoses() {
        when(diagnoseRepository.findAllByDeletedFalse())
                .thenReturn(Set.of(new DiagnoseEntity()));

        Set<DiagnoseEntity> actual = toTest.getAll();

        assertThatCollection(actual).isNotNull();
        assertThatCollection(actual).isNotEmpty();
        assertThatCollection(actual).doesNotContainNull();
        assertThatCollection(actual).hasSize(1);
        assertThat(actual.iterator().next()).isExactlyInstanceOf(DiagnoseEntity.class);
    }

    @Test
    public void testGetAll_ThrowsNoSuchDiagnoseEntityFoundExceptionIfEntityNotFound() {
        assertThatExceptionOfType(NoSuchDiagnoseEntityFoundException.class)
                .isThrownBy(() -> toTest.getAll());
    }

    @Test
    public void testGetAllToDTO_ReturnsCollectionWithAllDiagnosesDTO() {
        when(diagnoseRepository.findAllByDeletedFalse())
                .thenReturn(Set.of(new DiagnoseEntity()));

        when(diagnoseMapper.allToDTO(diagnoseRepository.findAllByDeletedFalse()))
                .thenReturn(Set.of(new DiagnoseDTO()));

        Set<DiagnoseDTO> actual = toTest.getAllToDTO();

        assertThatCollection(actual).isNotNull();
        assertThatCollection(actual).isNotEmpty();
        assertThatCollection(actual).doesNotContainNull();
        assertThatCollection(actual).hasSize(1);
        assertThat(actual.iterator().next()).isExactlyInstanceOf(DiagnoseDTO.class);
    }

    @Test
    public void testGetAllToDTO_ThrowsNoSuchDiagnoseEntityFoundExceptionIfEntityNotFound() {
        assertThatExceptionOfType(NoSuchDiagnoseEntityFoundException.class)
                .isThrownBy(() -> toTest.getAllToDTO());
    }

    @Test
    public void testCreate_ReturnsDiagnoseDTOOfCreatedDiagnose() {
        CreateDiagnoseDTO createDiagnoseDTO = new CreateDiagnoseDTO();
        ReflectionTestUtils.setField(createDiagnoseDTO,"name", "FLU");

        DiagnoseEntity diagnoseEntity = new DiagnoseEntity();
        ReflectionTestUtils.setField(diagnoseEntity,"name", "FLU");

        DiagnoseDTO diagnoseDTO = new DiagnoseDTO();
        ReflectionTestUtils.setField(diagnoseDTO,"name", "FLU");

        when(diagnoseMapper.toEntity(createDiagnoseDTO))
                .thenReturn(diagnoseEntity);

        when(diagnoseRepository.save(diagnoseMapper.toEntity(createDiagnoseDTO)))
                .then(AdditionalAnswers.returnsFirstArg());

        when(diagnoseMapper.toDTO(diagnoseRepository.save(diagnoseMapper.toEntity(createDiagnoseDTO))))
                .thenReturn(diagnoseDTO);

        DiagnoseDTO actual = toTest.create(createDiagnoseDTO);

        assertThat(actual.getName()).isEqualTo("FLU");
        assertThat(actual).isExactlyInstanceOf(DiagnoseDTO.class);
    }

    @Test
    public void testGetDiagnoseVisitCount_ReturnsDiagnoseVisitDTOOfCountOfVisitsWhereDiagnosisIsChosen() {
        DiagnoseVisitDTO diagnoseVisitDTO = new DiagnoseVisitDTO();
        ReflectionTestUtils.setField(diagnoseVisitDTO, "countVisits", 1L);

        when(diagnoseRepository.countVisitsByDiagnoseName("FLU"))
                .thenReturn(1L);

        when(diagnoseMapper.toDTO(diagnoseRepository.countVisitsByDiagnoseName("FLU")))
                .thenReturn(diagnoseVisitDTO);

        DiagnoseVisitDTO actual = toTest.getDiagnoseVisitCount("FLU");

        assertThat(actual.getCountVisits()).isEqualTo(1L);
        assertThat(actual).isExactlyInstanceOf(DiagnoseVisitDTO.class);
    }

    @Test
    public void testGetDiagnoseIncomeByName_ReturnsDiagnoseIncomeDTOOfIncomeFromAppointmentsWhereDiagnosisIsChosen() {
        DiagnoseIncomeDTO diagnoseIncomeDTO = new DiagnoseIncomeDTO();
        ReflectionTestUtils.setField(diagnoseIncomeDTO, "income", BigDecimal.TEN);

        when(diagnoseRepository.getTotalIncomeOfDiagnoseName("FLU"))
                .thenReturn(BigDecimal.TEN);

        when(diagnoseMapper.toDTO(diagnoseRepository.getTotalIncomeOfDiagnoseName("FLU")))
                .thenReturn(diagnoseIncomeDTO);

        DiagnoseIncomeDTO actual = toTest.getDiagnoseIncomeByName("FLU");

        assertThat(actual.getIncome().doubleValue()).isEqualTo(10.00);
        assertThat(actual).isExactlyInstanceOf(DiagnoseIncomeDTO.class);
    }

}
