package com.zerobase.accomodation.service.accomodation;

import com.zerobase.accomodation.domain.entity.accomodation.AccomodationReview;
import com.zerobase.accomodation.domain.entity.accomodation.AccomodationWishList;
import com.zerobase.accomodation.domain.repository.accomodation.AccomodationWishListRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccomodationWishService {

    private final AccomodationWishListRepository accomodationWishListRepository;

    public AccomodationWishList addAccomodationwish(Long memberId, Long accomodationId) {
       Optional<AccomodationWishList> accomodationWishListOptional =  accomodationWishListRepository.findByMemberIdAndAccomodationId(memberId,accomodationId);
       if(accomodationWishListOptional.isPresent()){
           accomodationWishListRepository.deleteByMemberIdAndAccomodationId(memberId,accomodationId);
       }

        return AccomodationWishList.builder().memberId(memberId)
            .accomodationId(accomodationId)
            .build();
    }

    public void deleteAccomodationwish(Long memberId, Long accomodationId) {
        accomodationWishListRepository.deleteByMemberIdAndAccomodationId(memberId,accomodationId);
    }
}
