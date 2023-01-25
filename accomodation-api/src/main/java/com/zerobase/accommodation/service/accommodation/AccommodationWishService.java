package com.zerobase.accommodation.service.accommodation;

import com.zerobase.accommodation.domain.dto.accommodation.AccommodationWishListDto;
import com.zerobase.accommodation.domain.entity.accommodation.Accommodation;
import com.zerobase.accommodation.domain.entity.accommodation.AccommodationWishList;
import com.zerobase.accommodation.domain.repository.accommodation.AccommodationRepository;
import com.zerobase.accommodation.domain.repository.accommodation.AccommodationWishListRepository;
import com.zerobase.accommodation.domain.type.ErrorCode;
import com.zerobase.accommodation.exception.AccommodationException;
import java.util.ArrayList;
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
public class AccommodationWishService {

    private final AccommodationWishListRepository accommodationWishListRepository;
    private final AccommodationRepository accommodationRepository;

    public AccommodationWishList addAccommodationWish(Long memberId, Long accommodationId) {
       accommodationRepository.findById(accommodationId).orElseThrow(() -> new AccommodationException(ErrorCode.NOT_FOUND_ACCOMMODATION));

        if (accommodationWishListRepository.findByMemberIdAndAccommodationId(memberId, accommodationId)
            .isPresent()) {
            throw new AccommodationException(ErrorCode.ALREADY_WISHED_ACCOMMODATION);
        }

        return accommodationWishListRepository.save(AccommodationWishList.builder().memberId(memberId)
            .accommodationId(accommodationId)
            .build());
    }

    public void deleteAccommodationWish(Long memberId, Long accommodationId) {
        accommodationWishListRepository.delete(accommodationWishListRepository.findByMemberIdAndAccommodationId(memberId, accommodationId)
            .orElseThrow(() -> new AccommodationException(ErrorCode.NOT_WISHED_ACCOMMODATION)));
    }


    public Page<AccommodationWishListDto> getAccommodationWishList(Long memberId, Pageable pageable) {
        Pageable limit = PageRequest.of(pageable.getPageNumber(), 15, Sort.by("id"));

        Page<AccommodationWishList> accommodationWishLists = accommodationWishListRepository.findAllByMemberId(memberId, limit)
            .orElseThrow(() -> new AccommodationException(ErrorCode.NOT_HAD_WISHLIST));

        List<AccommodationWishListDto> accommodationWishListDtoList = new ArrayList<>();

        for(AccommodationWishList accommodationWishList : accommodationWishLists.toList()){
            Accommodation accommodation = accommodationRepository.findById(accommodationWishList.getAccommodationId()).orElseThrow(() -> new AccommodationException(
                ErrorCode.NOT_FOUND_ACCOMMODATION));

            accommodationWishListDtoList.add(
                AccommodationWishListDto.builder()
                    .wishListId(accommodationWishList.getId())
                    .id(accommodationWishList.getAccommodationId())
                    .memberId(memberId)
                    .name(accommodation.getAccommodationName())
                    .addr(accommodation.getAddr())
                    .price(accommodation.getPrice())
                    .pictureUrl(accommodation.getPictureUrl())
                    .build()
            );
        }
        return new PageImpl<>(accommodationWishListDtoList, limit, accommodationWishLists.getTotalElements());
    }
}
