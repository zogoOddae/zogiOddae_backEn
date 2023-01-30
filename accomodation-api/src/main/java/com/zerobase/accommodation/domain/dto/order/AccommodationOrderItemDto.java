package com.zerobase.accommodation.domain.dto.order;

import com.zerobase.accommodation.domain.entity.accommodation.Accommodation;
import com.zerobase.accommodation.domain.entity.order.AccommodationOrderItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccommodationOrderItemDto {
    private Long orderItemId;
    private Long sellerId;

    private Long productId;

    private Long couponId;
    private Integer salePrice;

    private String name;
    private Integer price;

    private String pictureUrl;

    private Integer persons;

    private String startAt;
    private String endAt;

    public static AccommodationOrderItemDto from(AccommodationOrderItem accommodationOrderItem, Accommodation accommodation) {
        return AccommodationOrderItemDto.builder()
            .orderItemId(accommodationOrderItem.getId())
            .sellerId(accommodation.getSellerId())
            .productId(accommodation.getId())
            .couponId(accommodationOrderItem.getCouponId())
            .salePrice(accommodationOrderItem.getSalePrice())
            .name(accommodation.getAccommodationName())
            .price(accommodation.getPrice())
            .price(accommodationOrderItem.getPrice())
            .persons(accommodationOrderItem.getPersons())
            .startAt(accommodationOrderItem.getStartAt().toString())
            .endAt(accommodationOrderItem.getEndAt().toString())
            .build();
    }
}