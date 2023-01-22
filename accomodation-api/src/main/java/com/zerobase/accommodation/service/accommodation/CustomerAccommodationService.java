package com.zerobase.accommodation.service.accommodation;

import com.zerobase.accommodation.domain.dto.accommodation.AccommodationListDto;
import com.zerobase.accommodation.domain.entity.accommodation.Accommodation;
import com.zerobase.accommodation.domain.repository.accommodation.AccommodationRepository;
import com.zerobase.accommodation.domain.type.ErrorCode;
import com.zerobase.accommodation.exception.AccommodationException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerAccommodationService {

    private final AccommodationRepository accommodationRepository;

    public Page<AccommodationListDto> getAllaccommodation(Long sellerId, Pageable pageable) {
        Pageable limit = PageRequest.of(pageable.getPageNumber(), 15, Sort.by("id"));

        Page<Accommodation> accommodationPage = accommodationRepository.findAllBySellerId(sellerId, limit);

        List<AccommodationListDto> accommodationDtos = AccommodationListDto.fromList(accommodationPage.toList());

        return new PageImpl<>(accommodationDtos, limit, accommodationPage.getTotalElements());

    }

    public Accommodation getDetailAccommodation(Long accommodationId) {
        return accommodationRepository.findById(accommodationId)
            .orElseThrow(() -> new AccommodationException(ErrorCode.NOT_FOUND_ACCOMMODATION));

    }
}
