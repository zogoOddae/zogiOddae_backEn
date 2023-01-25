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
public class AccommodationOrderItemListDto {
    private Long orderItemId;
    private Long sellerId;

    private String name;

    private String pictureUrl;

    private Integer persons;

    private String startAt;
    private String endAt;

    public static AccommodationOrderItemListDto from(AccommodationOrderItem accommodationOrderItem, Accommodation accommodation) {
        return AccommodationOrderItemListDto.builder()
            .orderItemId(accommodationOrderItem.getId())
            .sellerId(accommodation.getSellerId())
            .pictureUrl(accommodation.getPictureUrl())
            .name(accommodation.getAccommodationName())
            .persons(accommodationOrderItem.getPersons())
            .startAt(accommodationOrderItem.getStartAt().toString())
            .endAt(accommodationOrderItem.getEndAt().toString())
            .build();
    }
}
