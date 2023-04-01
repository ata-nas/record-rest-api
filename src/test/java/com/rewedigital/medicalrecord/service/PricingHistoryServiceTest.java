package com.rewedigital.medicalrecord.service;

import com.rewedigital.medicalrecord.exception.notfound.NoSuchPricingHistoryEntityFoundException;
import com.rewedigital.medicalrecord.model.dto.pricing.CreatePricingHistoryDTO;
import com.rewedigital.medicalrecord.model.dto.pricing.PricingHistoryDTO;
import com.rewedigital.medicalrecord.model.dto.pricing.UpdatePricingHistoryDTO;
import com.rewedigital.medicalrecord.model.entity.PricingHistoryEntity;
import com.rewedigital.medicalrecord.model.mapper.PricingHistoryMapper;
import com.rewedigital.medicalrecord.repository.PricingHistoryRepository;
import com.rewedigital.medicalrecord.service.impl.PricingHistoryServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

@ExtendWith(MockitoExtension.class)
class PricingHistoryServiceTest {

    @Mock
    private PricingHistoryRepository pricingHistoryRepository;

    @Mock
    private PricingHistoryMapper pricingHistoryMapper;

    @InjectMocks
    private PricingHistoryServiceImpl toTest;

    @BeforeEach
    public void init() {
        openMocks(pricingHistoryRepository);
        openMocks(pricingHistoryMapper);
    }

    @Test
    public void testGetPricingByIssueNo_ReturnsPricingHistoryByIssueNo() {
        PricingHistoryEntity expected = new PricingHistoryEntity();
        ReflectionTestUtils.setField(expected,"issueNo", "1");

        when(pricingHistoryRepository.findByIssueNo("1"))
                .thenReturn(Optional.of(expected));

        PricingHistoryEntity actual = toTest.getByIssueNo("1");

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testGetPricingByIssueNo_ThrowsNoSuchPricingHistoryEntityFoundExceptionIfEntityNotFound() {
        assertThatExceptionOfType(NoSuchPricingHistoryEntityFoundException.class)
                .isThrownBy(() -> toTest.getByIssueNo("wrong value"));
    }

    @Test
    public void testGetAll_ReturnsCollectionWithAllPricingHistory() {
        when(pricingHistoryRepository.findAll())
                .thenReturn(List.of(new PricingHistoryEntity()));

        List<PricingHistoryEntity> actual = toTest.getAll();

        assertThatCollection(actual).isNotNull();
        assertThatCollection(actual).isNotEmpty();
        assertThatCollection(actual).doesNotContainNull();
        assertThatCollection(actual).hasSize(1);
        assertThat(actual.iterator().next()).isExactlyInstanceOf(PricingHistoryEntity.class);
    }

    @Test
    public void testGetAll_ThrowsNoSuchPricingHistoryEntityFoundExceptionIfEntityNotFound() {
        assertThatExceptionOfType(NoSuchPricingHistoryEntityFoundException.class)
                .isThrownBy(() -> toTest.getAll());
    }

    @Test
    public void testGetAllToDTO_ReturnsCollectionWithAllPricingHistoryDTO() {
        when(pricingHistoryRepository.findAll())
                .thenReturn(List.of(new PricingHistoryEntity()));

        when(pricingHistoryMapper.allToDTO(pricingHistoryRepository.findAll()))
                .thenReturn(List.of(new PricingHistoryDTO()));

        List<PricingHistoryDTO> actual = toTest.getAllToDTO();

        assertThatCollection(actual).isNotNull();
        assertThatCollection(actual).isNotEmpty();
        assertThatCollection(actual).doesNotContainNull();
        assertThatCollection(actual).hasSize(1);
        assertThat(actual.iterator().next()).isExactlyInstanceOf(PricingHistoryDTO.class);
    }

    @Test
    public void testGetAllToDTO_ThrowsNoSuchPricingHistoryEntityFoundExceptionIfEntityNotFound() {
        assertThatExceptionOfType(NoSuchPricingHistoryEntityFoundException.class)
                .isThrownBy(() -> toTest.getAllToDTO());
    }

    @Test
    public void testCreate_ReturnsSpecialtyDTOOfCreatedSpecialty() {
        CreatePricingHistoryDTO createPricingHistoryDTO = new CreatePricingHistoryDTO();
        ReflectionTestUtils.setField(createPricingHistoryDTO,"issueNo", "1");

        PricingHistoryEntity pricingHistoryEntity = new PricingHistoryEntity();
        ReflectionTestUtils.setField(pricingHistoryEntity,"issueNo", "1");

        PricingHistoryDTO pricingHistoryDTO = new PricingHistoryDTO();
        ReflectionTestUtils.setField(pricingHistoryDTO,"issueNo", "1");

        when(pricingHistoryMapper.toEntity(createPricingHistoryDTO))
                .thenReturn(pricingHistoryEntity);

        when(pricingHistoryRepository.save(pricingHistoryMapper.toEntity(createPricingHistoryDTO)))
                .then(AdditionalAnswers.returnsFirstArg());

        when(pricingHistoryMapper.toDTO(pricingHistoryRepository.save(pricingHistoryMapper.toEntity(createPricingHistoryDTO))))
                .thenReturn(pricingHistoryDTO);

        PricingHistoryDTO actual = toTest.create(createPricingHistoryDTO);

        assertThat(actual.getIssueNo()).isEqualTo("1");
        assertThat(actual).isExactlyInstanceOf(PricingHistoryDTO.class);
    }

    @Test
    public void testUpdate_ReturnsPricingHistoryDTOOfUpdatedPricingHistory() {
        UpdatePricingHistoryDTO updatePricingHistoryDTO = new UpdatePricingHistoryDTO();
        ReflectionTestUtils.setField(updatePricingHistoryDTO, "appointmentFees", BigDecimal.TEN);

        PricingHistoryEntity pricingHistoryEntity = new PricingHistoryEntity();
        ReflectionTestUtils.setField(pricingHistoryEntity, "appointmentFees", BigDecimal.TEN);

        PricingHistoryDTO pricingHistoryDTO = new PricingHistoryDTO();
        ReflectionTestUtils.setField(pricingHistoryDTO, "appointmentFees", BigDecimal.TEN);

        when(pricingHistoryRepository.findByIssueNo("1"))
                .thenReturn(Optional.of(pricingHistoryEntity));

        when(pricingHistoryMapper.toEntity(updatePricingHistoryDTO, pricingHistoryRepository.findByIssueNo("1").get()))
                .thenReturn(pricingHistoryEntity);

        when(pricingHistoryRepository.save(pricingHistoryMapper.toEntity(updatePricingHistoryDTO, pricingHistoryRepository.findByIssueNo("1").get())))
                .then(AdditionalAnswers.returnsFirstArg());

        when(pricingHistoryMapper.toDTO(pricingHistoryRepository.save(pricingHistoryMapper.toEntity(updatePricingHistoryDTO, pricingHistoryRepository.findByIssueNo("1").get()))))
                .thenReturn(pricingHistoryDTO);

        PricingHistoryDTO actual = toTest.update("1", updatePricingHistoryDTO);

        assertThat(actual.getAppointmentFees().doubleValue()).isEqualTo(10.0);
        assertThat(actual).isExactlyInstanceOf(PricingHistoryDTO.class);
    }

    @Test
    public void testUpdate_ThrowsNoSuchPricingHistoryEntityFoundExceptionIfEntityNotFound() {
        assertThatExceptionOfType(NoSuchPricingHistoryEntityFoundException.class)
                .isThrownBy(() -> toTest.getAllToDTO());
    }

}
