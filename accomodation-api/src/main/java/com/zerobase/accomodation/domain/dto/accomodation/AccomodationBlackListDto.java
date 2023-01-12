package com.zerobase.accomodation.domain.dto.accomodation;

import com.zerobase.accomodation.domain.entity.accomodation.AccomodationBlackList;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccomodationBlackListDto {

    private Long accomodationBlackListId;
    private Long accomodationId;

    private Long customerId;
    private String description;

    public static AccomodationBlackListDto from(AccomodationBlackList accomodationBlackList) {
        return AccomodationBlackListDto.builder()
            .accomodationBlackListId(accomodationBlackList.getId())
            .accomodationId(accomodationBlackList.getAccomodationId())
            .customerId(accomodationBlackList.getCustomerId())
            .description(accomodationBlackList.getDescription())
            .build();
    }

}
