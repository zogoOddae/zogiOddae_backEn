package com.zerobase.accommodation.service.accommodation;

import com.zerobase.accommodation.domain.dto.accommodation.AccommodationBlackListDto;
import com.zerobase.accommodation.domain.entity.accommodation.AccommodationBlackList;
import com.zerobase.accommodation.domain.form.accommodation.AddAccommodationBlackListForm;
import com.zerobase.accommodation.domain.repository.accommodation.AccommodationBlackListRepository;
import com.zerobase.accommodation.domain.repository.accommodation.AccommodationRepository;
import com.zerobase.accommodation.domain.type.ErrorCode;
import com.zerobase.accommodation.exception.AccommodationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccommodationBlackListService {

    private final AccommodationBlackListRepository accommodationBlackListRepository;
    private final AccommodationRepository accommodationRepository;

    public AccommodationBlackList addAccommodationBlackList(AddAccommodationBlackListForm form) {
        Optional<AccommodationBlackList> accommodationBlackListOptional =
            accommodationBlackListRepository.findByCustomerIdAndAccommodationId(
                form.getCustomerId(), form.getProductId());
        if (accommodationBlackListOptional.isPresent()) {
            throw new AccommodationException(ErrorCode.ALREADY_REGISTERED_BLACKLIST);
        }

        accommodationRepository.findById(form.getProductId())
            .orElseThrow(() -> new AccommodationException(ErrorCode.NOT_FOUND_ACCOMMODATION));

        AccommodationBlackList accommodationBlackList = AccommodationBlackList.builder()
            .accommodationId(form.getProductId())
            .customerId(form.getCustomerId())
            .description(form.getDescription())
            .build();

        accommodationBlackListRepository.save(accommodationBlackList);

        return accommodationBlackList;
    }

    public void deleteAccommodationBlackList(Long accommodationBlackListId) {
        AccommodationBlackList accommodationBlackList = accommodationBlackListRepository.findById(
                accommodationBlackListId)
            .orElseThrow(() -> new AccommodationException(ErrorCode.ALREADY_DELETED_BLACKLIST));

        accommodationRepository.findById(accommodationBlackList.getAccommodationId())
            .orElseThrow(() -> new AccommodationException(ErrorCode.NOT_FOUND_ACCOMMODATION));

        accommodationBlackListRepository.deleteById(accommodationBlackListId);
    }

    public Page<AccommodationBlackListDto> getAllAccommodationBlackList(Long accommodationId, Pageable pageable) {
        accommodationRepository.findById(accommodationId).orElseThrow(() -> new AccommodationException(ErrorCode.NOT_FOUND_ACCOMMODATION));

        Pageable limit = PageRequest.of(pageable.getPageNumber(), 15, Sort.by("customerId"));

        Page<AccommodationBlackList> accommodationBlackLists = accommodationBlackListRepository.findAllByAccommodationId(accommodationId,limit);
        List<AccommodationBlackListDto> accommodationBlackListDtos = new ArrayList<>();

        for(AccommodationBlackList accommodationBlackList : accommodationBlackLists ){
            accommodationBlackListDtos.add(AccommodationBlackListDto.from(accommodationBlackList));
        }

        return new PageImpl<>(accommodationBlackListDtos, limit,accommodationBlackLists.getTotalElements());
    }
}
