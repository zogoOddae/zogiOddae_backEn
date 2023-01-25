package com.zerobase.accommodation.domain.dto.order;

import com.zerobase.accommodation.domain.entity.accommodation.Accommodation;
import com.zerobase.accommodation.domain.entity.order.AccommodationOrder;
import com.zerobase.accommodation.domain.entity.order.AccommodationOrderItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccommodationOrderListDto {
    private Long orderId;
    private String reservationAt;

    private String reservationId;

    private String name;
    private String pictureUrl;
    private Integer persons;
    private Integer orderCount; //주문 개수
    private String startAt;
    private String endAt;

    public static AccommodationOrderListDto from(AccommodationOrder accommodationOrder, AccommodationOrderItem accommodationOrderItem, Accommodation accommodation
        ,int cnt) {
        return AccommodationOrderListDto.builder()
            .reservationAt(accommodationOrder.getCreateAt().toString())
            .orderId(accommodationOrder.getId())
            .reservationId(accommodationOrder.getReservationId())
            .name(accommodation.getAccommodationName())
            .pictureUrl(accommodation.getPictureUrl())
            .persons(accommodationOrderItem.getPersons())
            .orderCount(cnt)
            .startAt(accommodationOrderItem.getStartAt().toString())
            .endAt(accommodationOrderItem.getEndAt().toString())
            .build();
    }

}
