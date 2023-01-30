package com.zerobase.accommodation.domain.dto.order;

import com.zerobase.accommodation.domain.entity.order.AccommodationOrder;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccommodationOrderDto {
    private Long orderId;

    private String reservationAt;

    private String reservationId;

    private String customerName;

    private String email;

    private List<AccommodationOrderItemListDto> orderItemList;

    public static AccommodationOrderDto from(AccommodationOrder accommodationOrder, List<AccommodationOrderItemListDto> accommodationOrderItemDtoList) {
        return AccommodationOrderDto.builder()
            .reservationAt(accommodationOrder.getCreateAt().toString())
            .orderId(accommodationOrder.getId())
            .reservationId(accommodationOrder.getReservationId())
            .customerName("String")
            .email("String")
            .orderItemList(accommodationOrderItemDtoList)
            .build();
    }
}
