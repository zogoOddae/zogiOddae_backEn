package com.zerobase.accommodation.service.accommodation;

import static com.zerobase.accommodation.domain.type.Category.MOUNTAIN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.zerobase.accommodation.domain.entity.accommodation.Accommodation;
import com.zerobase.accommodation.domain.entity.accommodation.AccommodationDayOff;
import com.zerobase.accommodation.domain.form.accommodation.AccommodationDayOffForm;
import com.zerobase.accommodation.domain.form.accommodation.AccommodationForm;
import com.zerobase.accommodation.domain.repository.accommodation.AccommodationDayOffRepository;
import com.zerobase.accommodation.domain.repository.accommodation.AccommodationRepository;
import com.zerobase.accommodation.domain.type.ErrorCode;
import com.zerobase.accommodation.exception.AccommodationException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SellerAccommodationServiceTest {

    @Mock
    private AccommodationRepository accommodationRepository;

    @Mock
    private AccommodationDayOffRepository accommodationDayOffRepository;

    @InjectMocks
    private SellerAccommodationService service;

    private final AccommodationForm accommodationForm = AccommodationForm.builder()
        .name("테스트용 숙박")
        .addr("서울")
        .price(10000)
        .description("테스트입니다")
        .category("MOUNTAIN")
        .minPerson(5)
        .maxPerson(10)
        .build();
    private final Long sellerId = 10L;

    private final Accommodation accommodation = Accommodation.builder()
        .accommodationName("테스트용 숙박")
        .addr("서울")
        .price(10000)
        .category(MOUNTAIN)
        .description("테스트입니다")
        .minPerson(5)
        .maxPerson(10)
        .build();

    private final AccommodationDayOffForm accommodationDayOffForm = AccommodationDayOffForm.builder()
        .startAt(LocalDate.now())
        .endAt(LocalDate.MAX)
        .build();

    private final AccommodationDayOff accommodationDayOff = AccommodationDayOff.builder()
        .dayOffYear("2022")
        .startAt(LocalDate.now())
        .endAt(LocalDate.MAX)
        .build();


    @DisplayName("AddAccommodation - success")
    @Test
    void addAccommodation() {
        //when
        final Accommodation accommodation = service.addAccommodation(1L, accommodationForm);

        //then
        assertThat(accommodation.getAccommodationName()).isEqualTo(
            accommodationForm.getName());
        assertThat(accommodation.getSellerId()).isEqualTo(1L);

        verify(accommodationRepository, times(1)).save(any(Accommodation.class));
    }

    @DisplayName("getAllAccommodationList - success")
    @Test
    void getAllAccommodation() {
        //given
//        Pageable limit = PageRequest.of(0, 15);
//        List<Accommodation> list = new ArrayList<>();
//
//        list.add(accommodation);
//
//        Page listPage = new PageImpl<>(list);
//
//        given(accommodationRepository.findAllBySellerId(sellerId,limit)).willReturn(Optional.of(listPage));
//
//        //when
//        Page<AccommodationListDto> accommodationListDtos = service.getAllAccommodation(sellerId,limit);
//
//        //then
//        assertThat(accommodationListDtos).isNotNull();
//        assertThat(accommodationListDtos.getContent().contains(sellerId));
//        verify(accommodationRepository, times(1)).findAllBySellerId(sellerId,limit);
    }

    @DisplayName("updateAccommodation - success")
    @Test
    void updateAccommodation() {
        //given
        Accommodation accommodation2 = Accommodation.builder()
            .accommodationName("업데이트 테스트")
            .addr("서울")
            .price(123455)
            .description("업데이트 전")
            .minPerson(5)
            .maxPerson(10)
            .build();
        given(accommodationRepository.findById(1L)).willReturn(Optional.of(accommodation2));

        //when
        final Accommodation accommodation1 = service.updateAccommodation(1L, accommodationForm);

        //then
        assertThat(accommodation1).isNotNull();
        assertEquals(accommodation1.getAccommodationName(),
            accommodationForm.getName());
        assertEquals(accommodation1.getDescription(), accommodationForm.getDescription());
    }


    @DisplayName("updateAccommodation - fail")
    @Test
    void updateAccommodationFail() {
        //given
        given(accommodationRepository.findById(1L)).willReturn(Optional.empty());

        //when
        final AccommodationException result = assertThrows(AccommodationException.class,
            () -> service.updateAccommodation(1L, accommodationForm));

        //then
        assertThat(result.getErrorCode()).isEqualTo(ErrorCode.NOT_FOUND_ACCOMMODATION);
    }

    @DisplayName("getDetailAccommodation - success")
    @Test
    void getDetailAccommodation() {
        //given
        given(accommodationRepository.findByIdAndSellerId(1L,sellerId)).willReturn(
            Optional.of(accommodation));

        //when
        final Accommodation accommodation1 = service.getDetailAccommodation(1L, sellerId);

        //then
        assertThat(accommodation1).isNotNull();
        assertEquals(accommodation1.getAccommodationName(), accommodation.getAccommodationName());
        assertEquals(accommodation1.getId(), accommodation.getId());
        verify(accommodationRepository, times(1)).findByIdAndSellerId(1L,sellerId);

    }

    @DisplayName("getDetailAccommodation - fail")
    @Test
    void getDetailAccommodationFail() {
        //given
        given(accommodationRepository.findByIdAndSellerId(1L,sellerId)).willReturn(Optional.empty());

        //when
        final AccommodationException result = assertThrows(AccommodationException.class,
            () -> service.getDetailAccommodation(1L, sellerId));

        //then
        assertThat(result.getErrorCode()).isEqualTo(ErrorCode.NOT_FOUND_ACCOMMODATION);
    }

    @DisplayName("deleteAccommodation - success")
    @Test
    void deleteAccommodation() {
        //given
        given(accommodationRepository.findByIdAndSellerId(1L,sellerId)).willReturn(
            Optional.of(accommodation));

        //when
        service.deleteAccommodation(1L, sellerId);

        //then

        Optional<Accommodation> accommodation1 = accommodationRepository.findById(1L);

        assertThat(accommodation1).isEmpty();
        verify(accommodationRepository, times(1)).deleteById(1L);
    }

    @DisplayName("addAccommodationDayOff - success")
    @Test
    void addAccommodationDayOff(){
        //given
        given(accommodationDayOffRepository.findByAccommodationId(1L)).willReturn(Optional.of(accommodationDayOff));

        //when
        service.addAccommodationDayOff(1L, accommodationDayOffForm);

        //then
        Optional<AccommodationDayOff> accommodationDayOff1 = accommodationDayOffRepository.findByAccommodationId(1L);

        assertThat(accommodationDayOff1.get().getStartAt()).isEqualTo(accommodationDayOffForm.getStartAt());
        assertThat(accommodationDayOff1.get().getEndAt()).isEqualTo(accommodationDayOffForm.getEndAt());
        verify(accommodationDayOffRepository, times(1)).save(any(AccommodationDayOff.class));
    }

    @DisplayName("addAccommodationDayOff - success")
    @Test
    void deleteAccommodationDayOff() {
        //given
        given(accommodationDayOffRepository.findById(1L)).willReturn(Optional.of(accommodationDayOff));
        //when
        service.deleteAccommodationDayOff(1L);

        //then
        Optional<AccommodationDayOff> accommodationDayOff1 = accommodationDayOffRepository.findByAccommodationId(1L);

        assertThat(accommodationDayOff1).isEmpty();
        verify(accommodationDayOffRepository, times(1)).deleteById(1L);
    }

    @DisplayName("getAccommodationDayOff - success")
    @Test
    void getAccommodationDayOff() {
        //given
        List<AccommodationDayOff> list = new ArrayList<>();

        list.add(accommodationDayOff);

        given(accommodationDayOffRepository.findAllByAccommodationId(1L)).willReturn(Optional.of(list));

        //when
        final Optional<List<AccommodationDayOff>> accommodationDayOffs = accommodationDayOffRepository.findAllByAccommodationId(1L);

        //then
        assertThat(accommodationDayOffs).isNotNull();
        assertEquals(accommodationDayOffs.get().get(0).getStartAt(), accommodationDayOff.getStartAt());
        verify(accommodationDayOffRepository, times(1)).findAllByAccommodationId(1L);
    }


    @DisplayName("getAccommodationDayOff - fail")
    @Test
    void getAccommodationDayOffFail() {
        //when
        final AccommodationException result = assertThrows(AccommodationException.class, () -> service.getAccommodationDayOff(1L));

        //then
        assertThat(result.getErrorCode()).isEqualTo(ErrorCode.NOT_FOUND_ACCOMMODATION_DAY_OFF);
    }

    @DisplayName("updateAccommodationDayOff - success")
    @Test
    void updateAccommodationDayOff() {
        //given
        AccommodationDayOff accommodationDayOff2 = AccommodationDayOff.builder()
            .dayOffYear("2023")
            .startAt(LocalDate.parse("2022-12-12"))
            .endAt(LocalDate.now())
            .build();
        given(accommodationDayOffRepository.findById(1L)).willReturn(Optional.of(accommodationDayOff2));

        //when
        service.updateAccommodationDayOff(1L, accommodationDayOffForm);

        //then
        Optional<AccommodationDayOff> accommodationDayOff1 = accommodationDayOffRepository.findById(1L);

        assertThat(accommodationDayOff1).isNotNull();
        assertEquals(accommodationDayOff1.get().getStartAt(), accommodationDayOffForm.getStartAt());
        assertEquals(accommodationDayOff1.get().getEndAt(), accommodationDayOffForm.getEndAt());
    }


    @DisplayName("updateAccommodationDayOff - fail")
    @Test
    void updateAccommodationDayOffFail() {
        //given
        given(accommodationDayOffRepository.findById(1L)).willReturn(Optional.empty());

        //when
        final AccommodationException result = assertThrows(AccommodationException.class,
            () -> service.updateAccommodationDayOff(1L, accommodationDayOffForm));

        //then
        assertThat(result.getErrorCode()).isEqualTo(ErrorCode.NOT_FOUND_ACCOMMODATION_DAY_OFF);
    }


}