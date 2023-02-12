package com.zerobase.accommodation.service.accommodation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.zerobase.accommodation.domain.dto.accommodation.AccommodationBlackListDto;
import com.zerobase.accommodation.domain.entity.accommodation.Accommodation;
import com.zerobase.accommodation.domain.entity.accommodation.AccommodationBlackList;
import com.zerobase.accommodation.domain.form.accommodation.AddAccommodationBlackListForm;
import com.zerobase.accommodation.domain.repository.accommodation.AccommodationBlackListRepository;
import com.zerobase.accommodation.domain.repository.accommodation.AccommodationRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AccommodationBlackListServiceTest {

    @Mock
    private AccommodationRepository accommodationRepository;

    @Mock
    private AccommodationBlackListRepository accommodationBlackListRepository;

    @InjectMocks
    private AccommodationBlackListService service;

    private final AddAccommodationBlackListForm form = AddAccommodationBlackListForm
        .builder()
        .productId(1L)
        .customerId(12L)
        .description("블랙리스트 테스트")
        .build();

    private final AccommodationBlackList accommodationBlackList =
        AccommodationBlackList.builder()
            .id(1L)
            .accommodationId(1L)
            .customerId(15L)
            .description("테스트용")
            .build();
    private final Accommodation accommodation =
        Accommodation.builder().accommodationName("테스트").addr("서울").build();
    @Test
    void addAccommodationBlackList() {
        //given
        given(accommodationBlackListRepository.findByCustomerIdAndAccommodationId(12L, 1L))
            .willReturn(Optional.empty());

        given(accommodationRepository.findById(form.getProductId())).willReturn(
            Optional.of(accommodation));

        //when
        AccommodationBlackList accommodationBlackList = service.addAccommodationBlackList(form);

        //then

        assertThat(accommodationBlackList.getDescription()).isEqualTo(form.getDescription());
        assertThat(accommodationBlackList.getCustomerId()).isEqualTo(12L);

        verify(accommodationBlackListRepository, times(1)).save(any(AccommodationBlackList.class));
    }

    @Test
    void deleteAccommodationBlackList() {
        //given
        given(accommodationBlackListRepository.findById(anyLong())).willReturn(
            Optional.of(accommodationBlackList));

        given(accommodationRepository.findById(accommodationBlackList.getAccommodationId())).willReturn(
            Optional.of(accommodation));

        //when
        service.deleteAccommodationBlackList(1L);

        //then
        verify(accommodationBlackListRepository, times(1)).deleteById(1L);
    }

    @Test
    void getAllAccommodationBlackList(){
        //given
        given(accommodationRepository.findById(accommodation.getId())).willReturn(Optional.of(accommodation));

        Pageable limit = PageRequest.of(0, 15, Sort.by("customerId"));
        List<AccommodationBlackList> list = new ArrayList<>();

        list.add(accommodationBlackList);

        Page listPage = new PageImpl<>(list);

        given(accommodationBlackListRepository.findAllByAccommodationId(accommodation.getId(),limit)).willReturn(listPage);

        //when
        Page<AccommodationBlackListDto> accommodationBlackListDtos = service.getAllAccommodationBlackList(accommodation.getId(),limit);

        //then
        assertThat(accommodationBlackListDtos).isNotNull();
        assertThat(accommodationBlackListDtos.getContent().contains(accommodation.getId()));
    }
}