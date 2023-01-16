package com.zerobase.accommodation.service.accommodation;

import com.zerobase.accommodation.domain.dto.accommodation.AccommodationListDto;
import com.zerobase.accommodation.domain.entity.accommodation.Accommodation;
import com.zerobase.accommodation.domain.entity.accommodation.AccommodationDayOff;
import com.zerobase.accommodation.domain.form.AccommodationForm;
import com.zerobase.accommodation.domain.repository.accommodation.AccommodationDayOffRepository;
import com.zerobase.accommodation.domain.repository.accommodation.AccommodationRepository;
import com.zerobase.accommodation.domain.type.ErrorCode;
import com.zerobase.accommodation.exception.AccommodationException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SellerAccommodationService {

    private final AccommodationRepository accommodationRepository;
    private final AccommodationDayOffRepository accommodationDayOffRepository;

    public Accommodation addAccommodation(Long sellerId, AccommodationForm form) {
        Accommodation accommodation = Accommodation.of(sellerId, form);
        accommodationRepository.save(accommodation);

        String dayOffStart = form.getDayOffStartDate()
            .format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        accommodationDayOffRepository.save(AccommodationDayOff.builder()
            .accommodationId(accommodation.getId())
            .year(dayOffStart.substring(0, 4))
            .startDate(form.getDayOffStartDate())
            .endDate(form.getDayOffEndDate())
            .build());

        return accommodation;
    }

    public List<AccommodationListDto> getAllAccommodation(Long sellerId) {
        List<Accommodation> accommodationList = accommodationRepository.findAllBySellerId(sellerId)
            .orElseThrow(() -> new AccommodationException(ErrorCode.NOT_FOUND_ACCOMODATION));

        List<AccommodationListDto> dtoList = new ArrayList<>();

        for (Accommodation accommodation : accommodationList) {
            dtoList.add(AccommodationListDto.from(accommodation));
        }
        return dtoList;
    }

    public Accommodation updateAccommodation(Long accommodationId, AccommodationForm form) {
        Accommodation accommodation = accommodationRepository.findById(accommodationId)
            .orElseThrow(() -> new AccommodationException(ErrorCode.NOT_FOUND_ACCOMODATION));

        accommodation.setAccommodationName(form.getAccommodationName());
        accommodation.setSellerId(accommodation.getSellerId());
        accommodation.setAddr(form.getAddr());
        accommodation.setDescription(form.getDescription());
        accommodation.setPrice(form.getPrice());
        accommodation.setPictureUrl(form.getPictureUrl());
        accommodation.setMaxPerson(form.getMaxPerson());
        accommodation.setMinPerson(form.getMinPerson());
        accommodation.setLat(form.getLat());
        accommodation.setLon(form.getLon());

		String dayOffStart = form.getDayOffStartDate().format(DateTimeFormatter.ofPattern("yyyymmdd"));

		Optional<AccommodationDayOff> accommodationDayOffOptional = accommodationDayOffRepository.findByAccommodationId(accommodationId);

		if(!accommodationDayOffOptional.isPresent()){
			accommodationDayOffRepository.save(AccommodationDayOff.builder()
				.accommodationId(accommodation.getId())
				.year(dayOffStart.substring(0, 4))
				.startDate(form.getDayOffStartDate())
				.endDate(form.getDayOffEndDate())
				.build());
		}else{
			AccommodationDayOff accommodationDayOff = accommodationDayOffOptional.get();

			accommodationDayOff.setAccommodationId(accommodationId);
			accommodationDayOff.setYear(dayOffStart.substring(0, 4));
			accommodationDayOff.setStartDate(form.getDayOffStartDate());
			accommodationDayOff.setEndDate(form.getDayOffEndDate());

			accommodationDayOffRepository.save(accommodationDayOff);
		}

        return accommodation;
    }

    public Accommodation getDetailAccommodation(Long accommodationId, Long sellerId) {
        return accommodationRepository.getFirstByIdAndSellerId(accommodationId, sellerId)
            .orElseThrow(() -> new AccommodationException(ErrorCode.NOT_FOUND_ACCOMODATION));
    }

    public void deleteAccommodation(Long accommodationId, Long sellerId) {
        accommodationRepository.getFirstByIdAndSellerId(accommodationId, sellerId)
            .orElseThrow(() -> new AccommodationException(ErrorCode.NOT_FOUND_ACCOMODATION));

        accommodationRepository.deleteById(accommodationId);
    }
}
