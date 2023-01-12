package com.zerobase.accomodation.service.accomodation;

import com.zerobase.accomodation.domain.dto.accomodation.AccomodationWishListDto;
import com.zerobase.accomodation.domain.entity.accomodation.Accomodation;
import com.zerobase.accomodation.domain.entity.accomodation.AccomodationWishList;
import com.zerobase.accomodation.domain.repository.accomodation.AccomodationRepository;
import com.zerobase.accomodation.domain.repository.accomodation.AccomodationWishListRepository;
import com.zerobase.accomodation.domain.type.ErrorCode;
import com.zerobase.accomodation.exception.AccomodationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccomodationWishService {

    private final AccomodationWishListRepository accomodationWishListRepository;
    private final AccomodationRepository accomodationRepository;

    public AccomodationWishList makeAccomodationwish(Long memberId, Long accomodationId) {
        Optional<AccomodationWishList> accomodationWishListOptional = accomodationWishListRepository.findByMemberId(memberId);

        Accomodation accomodation = accomodationRepository.findById(accomodationId).orElseThrow(() -> new AccomodationException(
            ErrorCode.NOT_FOUND_ACCOMODATION));

        List<Long> accomodationList = new ArrayList<>();
        accomodationList.add(accomodationId);

        AccomodationWishList accomodationWishList;

        if(!accomodationWishListOptional.isPresent()){
            //위시리스트 유무 확인
            accomodationWishList = AccomodationWishList.builder().memberId(memberId)
                .accomodationId(accomodationList)
                .build();
        }else{

            if (accomodationWishListOptional.get().getAccomodationId().contains(accomodationId)) {
                accomodationWishListOptional.get().getAccomodationId().remove(accomodationId);
            }else{
                accomodationWishListOptional.get().getAccomodationId().add(accomodationId);
            }
            accomodationWishList = accomodationWishListOptional.get();
        }

        return accomodationWishListRepository.save(accomodationWishList);
    }

    public void deleteAccomodationwish(Long memberId, Long accomodationId) {
        accomodationWishListRepository.deleteByMemberIdAndAccomodationId(memberId, accomodationId);
    }

    public Accomodation getAccomodationInfo(Long accomodationId) {
        return accomodationRepository.findById(accomodationId).orElseThrow(() -> new AccomodationException(
            ErrorCode.NOT_FOUND_ACCOMODATION));
    }

    public List<AccomodationWishListDto> getAllAccomodationWish(Long memberId) {
        Optional<AccomodationWishList> optionalAccomodationWishList = accomodationWishListRepository.findByMemberId(memberId);
        List<AccomodationWishListDto> accomodationWishListDtoList = new ArrayList<>();
        if(!optionalAccomodationWishList.isPresent()){
            return accomodationWishListDtoList;
        }

        for(Long accomodationId : optionalAccomodationWishList.get().getAccomodationId()){
            Accomodation accomodation = accomodationRepository.findById(accomodationId).orElseThrow(() -> new AccomodationException(
                ErrorCode.NOT_FOUND_ACCOMODATION));

            accomodationWishListDtoList.add(
                AccomodationWishListDto.builder()
                    .accomodationWishListId(optionalAccomodationWishList.get().getId())
                    .accomodationId(accomodationId)
                    .memberId(memberId)
                    .accomodationName(accomodation.getAccomodationName())
                    .addr(accomodation.getAddr())
                    .price(accomodation.getPrice())
                    .pictureUrl(accomodation.getPictureUrl())
                    .build()
            );
        }

        return accomodationWishListDtoList;
    }
}
