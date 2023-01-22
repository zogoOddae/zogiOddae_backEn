package com.zerobase.accommodation.domain.dto.accommodation;

import com.zerobase.accommodation.domain.entity.accommodation.Accommodation;
import com.zerobase.accommodation.domain.type.Category;
import java.util.List;
import java.util.stream.Collectors;
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

    public static CustomerAccommodationDto from(Accommodation accommodation) {
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
            .checkIn(accommodation.getCheckIn())
            .checkOut(accommodation.getCheckOut())
            .lat(accommodation.getLat())
            .lon(accommodation.getLon())
            .build();
    }

    public static List<CustomerAccommodationDto> fromList(List<Accommodation> accommodationListList) {
        return accommodationListList.stream()
            .map(CustomerAccommodationDto::from)
            .collect(Collectors.toList());
    }
}
