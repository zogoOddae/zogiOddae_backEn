package com.zerobase.accomodation.domain.dto.accomodation;

import com.zerobase.accomodation.domain.entity.accomodation.Accomodation;
import com.zerobase.accomodation.domain.entity.accomodation.AccomodationWishList;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccomodationWishListDto {

    private Long accomodationWishListId;
    private Long accomodationId;

    private Long memberId;
    private String accomodationName;
    private String addr;
    private Integer price;
    private String pictureUrl;


    public static AccomodationWishListDto from(AccomodationWishList accomodationWishList, Accomodation accomodation) {
        return AccomodationWishListDto.builder()
            .accomodationWishListId(accomodationWishList.getId())
            .accomodationId(accomodation.getId())
            .memberId(accomodationWishList.getMemberId())
            .accomodationName(accomodation.getAccomodationName())
            .addr(accomodation.getAddr())
            .price(accomodation.getPrice())
            .pictureUrl(accomodation.getPictureUrl())
            .build();
    }

}
