package com.zerobase.accommodation.service.accommodation;

import com.zerobase.accommodation.domain.entity.accommodation.AccommodationBlackList;
import com.zerobase.accommodation.domain.form.AddAccommodationBlackListForm;
import com.zerobase.accommodation.domain.repository.accommodation.AccommodationBlackListRepository;
import com.zerobase.accommodation.domain.repository.accommodation.AccommodationRepository;
import com.zerobase.accommodation.domain.type.ErrorCode;
import com.zerobase.accommodation.exception.AccommodationException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccommodationBlackListService {

    private final AccommodationBlackListRepository accommodationBlackListRepository;
    private final AccommodationRepository accommodationRepository;

    public AccommodationBlackList addAccommodationBlackList(AddAccommodationBlackListForm form) {
        Optional<AccommodationBlackList> accommodationBlackListOptional =
            accommodationBlackListRepository.findByCustomerIdAndAccommodationId(
                form.getCustomerId(), form.getAccommodationId());
        if (accommodationBlackListOptional.isPresent()) {
            throw new AccommodationException(ErrorCode.ALREADY_REGISTERED_BLACKLIST);
        }

        accommodationRepository.findById(form.getAccommodationId())
            .orElseThrow(() -> new AccommodationException(ErrorCode.NOT_FOUND_ACCOMMODATION));

        AccommodationBlackList accommodationBlackList = AccommodationBlackList.builder()
            .accommodationId(form.getAccommodationId())
            .customerId(form.getCustomerId())
            .description(form.getDescription())
            .build();

        accommodationBlackListRepository.save(accommodationBlackList);

        return accommodationBlackList;
    }

    public void deleteAccommodationBlackList(Long accomodationBlackListId) {
        AccommodationBlackList accommodationBlackList = accommodationBlackListRepository.findById(
                accomodationBlackListId)
            .orElseThrow(() -> new AccommodationException(ErrorCode.ALREADY_DELETED_BLACKLIST));

        accommodationRepository.findById(accommodationBlackList.getAccommodationId())
            .orElseThrow(() -> new AccommodationException(ErrorCode.NOT_FOUND_ACCOMMODATION));

        accommodationBlackListRepository.deleteById(accomodationBlackListId);
    }
}
