package com.zerobase.accommodation.domain.dto.accommodation;

import com.zerobase.accommodation.domain.entity.accommodation.Accommodation;
import com.zerobase.accommodation.domain.type.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerAccommodationDto {
    private Long id;
    private Long sellerId;

    private String name;
    private String addr;
    private Integer price;
    private String description;

    private String pictureUrl;

    private Integer minPerson;
    private Integer maxPerson;

    private String checkIn;
    private String checkOut;

    private Category category;

    private double lat;
    private double lon;

    public static CustomerAccommodationDto from(Accommodation accommodation, String startAt, String endAt) {
        return CustomerAccommodationDto.builder()
            .id(accommodation.getId())
            .sellerId(accommodation.getSellerId())
            .name(accommodation.getAccommodationName())
            .addr(accommodation.getAddr())
            .pictureUrl(accommodation.getPictureUrl())
            .price(accommodation.getPrice())
            .maxPerson(accommodation.getMaxPerson())
            .minPerson(accommodation.getMinPerson())
            .description(accommodation.getDescription())
            .checkIn(startAt)
            .checkOut(endAt)
            .lat(accommodation.getLat())
            .lon(accommodation.getLon())
            .build();
    }

}
