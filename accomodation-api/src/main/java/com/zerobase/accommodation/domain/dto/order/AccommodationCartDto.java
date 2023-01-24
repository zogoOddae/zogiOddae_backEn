package com.zerobase.accommodation.domain.dto.order;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccommodationCartDto {
    private Long cartId;

    private String customerName;

    private List<AccommodationOrderItemDto> orderItemList;

    private Integer totalPrice;
}
