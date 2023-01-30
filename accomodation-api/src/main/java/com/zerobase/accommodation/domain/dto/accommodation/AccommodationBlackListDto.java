package com.zerobase.accommodation.domain.dto.accommodation;

import com.zerobase.accommodation.domain.entity.accommodation.AccommodationBlackList;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccommodationBlackListDto {

    private Long blackListId;
    private Long id;

    private Long customerId;
    private String description;

    public static AccommodationBlackListDto from(AccommodationBlackList accommodationBlackList) {
        return AccommodationBlackListDto.builder()
            .blackListId(accommodationBlackList.getId())
            .id(accommodationBlackList.getAccommodationId())
            .customerId(accommodationBlackList.getCustomerId())
            .description(accommodationBlackList.getDescription())
            .build();
    }

}
