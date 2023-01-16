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
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccommodationWishService {

    private final AccommodationWishListRepository accommodationWishListRepository;
    private final AccommodationRepository accommodationRepository;

    public AccommodationWishList addAccommodationWish(Long memberId, Long accommodationId) {
       accommodationRepository.findById(accommodationId).orElseThrow(() -> new AccommodationException(ErrorCode.NOT_FOUND_ACCOMODATION));

        return accommodationWishListRepository.save(AccommodationWishList.builder().memberId(memberId)
            .accommodationId(accommodationId)
            .build());
    }

    public void deleteAccommodationWish(Long memberId, Long accommodationId) {
        accommodationWishListRepository.deleteByMemberIdAndAccommodationId(memberId, accommodationId);
    }

    public Accommodation getAccommodationInfo(Long accommodationId) {
        return accommodationRepository.findById(accommodationId).orElseThrow(() -> new AccommodationException(
            ErrorCode.NOT_FOUND_ACCOMODATION));
    }

    public List<AccommodationWishListDto> getAllAccommodationWish(Long memberId) {
        Optional<List<AccommodationWishList>> optionalAccommodationWishList = accommodationWishListRepository.findAllByMemberId(memberId);
        List<AccommodationWishListDto> accommodationWishListDtoList = new ArrayList<>();
        if(!optionalAccommodationWishList.isPresent()){
            return accommodationWishListDtoList;
        }

        for(AccommodationWishList accommodationWishList : optionalAccommodationWishList.get()){
            Accommodation accommodation = accommodationRepository.findById(accommodationWishList.getAccommodationId()).orElseThrow(() -> new AccommodationException(
                ErrorCode.NOT_FOUND_ACCOMODATION));

            accommodationWishListDtoList.add(
                AccommodationWishListDto.builder()
                    .accommodationWishListId(accommodationWishList.getId())
                    .accommodationId(accommodationWishList.getAccommodationId())
                    .memberId(memberId)
                    .accommodationName(accommodation.getAccommodationName())
                    .addr(accommodation.getAddr())
                    .price(accommodation.getPrice())
                    .pictureUrl(accommodation.getPictureUrl())
                    .build()
            );
        }

        return accommodationWishListDtoList;
    }
}
