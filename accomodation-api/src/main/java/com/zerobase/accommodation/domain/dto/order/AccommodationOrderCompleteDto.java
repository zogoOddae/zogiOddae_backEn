package com.zerobase.accommodation.domain.dto.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccommodationOrderCompleteDto {
    private String reservationId;
    private Integer price;
}
