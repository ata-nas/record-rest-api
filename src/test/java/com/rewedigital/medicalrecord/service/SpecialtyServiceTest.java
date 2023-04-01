package com.rewedigital.medicalrecord.service;

import com.rewedigital.medicalrecord.exception.notfound.NoSuchSpecialtyEntityFoundException;
import com.rewedigital.medicalrecord.model.dto.specialty.CreateSpecialtyDTO;
import com.rewedigital.medicalrecord.model.dto.specialty.SpecialtyDTO;
import com.rewedigital.medicalrecord.model.entity.SpecialtyEntity;
import com.rewedigital.medicalrecord.model.mapper.SpecialtyMapper;
import com.rewedigital.medicalrecord.repository.SpecialtyRepository;
import com.rewedigital.medicalrecord.service.impl.SpecialtyServiceImpl;

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
class SpecialtyServiceTest {

    @Mock
    private SpecialtyRepository specialtyRepository;

    @Mock
    private SpecialtyMapper specialtyMapper;

    @InjectMocks
    private SpecialtyServiceImpl toTest;

    @BeforeEach
    public void init() {
        openMocks(specialtyRepository);
        openMocks(specialtyMapper);
    }

    @Test
    public void testGetByName_ReturnsSpecialtyByName() {
        SpecialtyEntity expected = new SpecialtyEntity();
        ReflectionTestUtils.setField(expected,"name", "SURGERY");

        when(specialtyRepository.findByNameAndDeletedFalse("SURGERY"))
                .thenReturn(Optional.of(expected));

        SpecialtyEntity actual = toTest.getByName("SURGERY");

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testGetByName_ThrowsNoSuchSpecialtyEntityFoundExceptionIfEntityNotFound() {
        assertThatExceptionOfType(NoSuchSpecialtyEntityFoundException.class)
                .isThrownBy(() -> toTest.getByName("wrong value"));
    }

    @Test
    public void testGetByNameToDTO_ReturnsSpecialtyDTO() {
        SpecialtyEntity expected = new SpecialtyEntity();
        ReflectionTestUtils.setField(expected,"name", "SURGERY");

        SpecialtyDTO specialtyDTO = new SpecialtyDTO();
        ReflectionTestUtils.setField(specialtyDTO,"name", "SURGERY");

        when(specialtyRepository.findByNameAndDeletedFalse("SURGERY"))
                .thenReturn(Optional.of(expected));

        when(specialtyMapper.toDTO(specialtyRepository.findByNameAndDeletedFalse("SURGERY").get()))
                .thenReturn(specialtyDTO);

        SpecialtyDTO actual = toTest.getByNameToDTO("SURGERY");

        assertThat(actual.getName()).isEqualTo(expected.getName());
    }

    @Test
    public void testGetByNameToDTO_ThrowsNoSuchSpecialtyEntityFoundExceptionIfEntityNotFound() {
        assertThatExceptionOfType(NoSuchSpecialtyEntityFoundException.class)
                .isThrownBy(() -> toTest.getByNameToDTO("wrong value"));
    }

    @Test
    public void testGetAll_ReturnsCollectionWithAllSpecialties() {
        when(specialtyRepository.findAllByDeletedFalse())
                .thenReturn(Set.of(new SpecialtyEntity()));

        Set<SpecialtyEntity> actual = toTest.getAll();

        assertThatCollection(actual).isNotNull();
        assertThatCollection(actual).isNotEmpty();
        assertThatCollection(actual).doesNotContainNull();
        assertThatCollection(actual).hasSize(1);
        assertThat(actual.iterator().next()).isExactlyInstanceOf(SpecialtyEntity.class);
    }

    @Test
    public void testGetAll_ThrowsNoSuchSpecialtyEntityFoundExceptionIfEntityNotFound() {
        assertThatExceptionOfType(NoSuchSpecialtyEntityFoundException.class)
                .isThrownBy(() -> toTest.getAll());
    }

    @Test
    public void testGetAllToDTO_ReturnsCollectionWithAllSpecialtiesDTO() {
        when(specialtyRepository.findAllByDeletedFalse())
                .thenReturn(Set.of(new SpecialtyEntity()));

        when(specialtyMapper.allToDTO(specialtyRepository.findAllByDeletedFalse()))
                .thenReturn(Set.of(new SpecialtyDTO()));

        Set<SpecialtyDTO> actual = toTest.getAllToDTO();

        assertThatCollection(actual).isNotNull();
        assertThatCollection(actual).isNotEmpty();
        assertThatCollection(actual).doesNotContainNull();
        assertThatCollection(actual).hasSize(1);
        assertThat(actual.iterator().next()).isExactlyInstanceOf(SpecialtyDTO.class);
    }

    @Test
    public void testGetAllToDTO_ThrowsNoSuchSpecialtyEntityFoundExceptionIfEntityNotFound() {
        assertThatExceptionOfType(NoSuchSpecialtyEntityFoundException.class)
                .isThrownBy(() -> toTest.getAllToDTO());
    }

    @Test
    public void testCreate_ReturnsSpecialtyDTOOfCreatedSpecialty() {
        CreateSpecialtyDTO createSpecialtyDTO = new CreateSpecialtyDTO();
        ReflectionTestUtils.setField(createSpecialtyDTO,"name", "SURGERY");

        SpecialtyEntity specialtyEntity = new SpecialtyEntity();
        ReflectionTestUtils.setField(specialtyEntity,"name", "SURGERY");

        SpecialtyDTO specialtyDTO = new SpecialtyDTO();
        ReflectionTestUtils.setField(specialtyDTO,"name", "SURGERY");

        when(specialtyMapper.toEntity(createSpecialtyDTO))
                .thenReturn(specialtyEntity);

        when(specialtyRepository.save(specialtyMapper.toEntity(createSpecialtyDTO)))
                .then(AdditionalAnswers.returnsFirstArg());

        when(specialtyMapper.toDTO(specialtyRepository.save(specialtyMapper.toEntity(createSpecialtyDTO))))
                .thenReturn(specialtyDTO);

        SpecialtyDTO actual = toTest.create(createSpecialtyDTO);

        assertThat(actual.getName()).isEqualTo("SURGERY");
        assertThat(actual).isExactlyInstanceOf(SpecialtyDTO.class);
    }

}