package com.zerobase.accommodation.domain.dto.accommodation;

import com.zerobase.accommodation.domain.entity.accommodation.Accommodation;
import com.zerobase.accommodation.domain.entity.accommodation.AccommodationWishList;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccommodationWishListDto {

    private Long accommodationWishListId;
    private Long accommodationId;

    private Long customerId;
    private String accommodationName;
    private String addr;
    private Integer price;
    private String pictureUrl;


    public static AccommodationWishListDto from(AccommodationWishList accommodationWishList, Accommodation accommodation) {
        return AccommodationWishListDto.builder()
            .accommodationWishListId(accommodationWishList.getId())
            .accommodationId(accommodation.getId())
            .customerId(accommodationWishList.getMemberId())
            .accommodationName(accommodation.getAccommodationName())
            .addr(accommodation.getAddr())
            .price(accommodation.getPrice())
            .pictureUrl(accommodation.getPictureUrl())
            .build();
    }

}
