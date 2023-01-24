package com.zerobase.accommodation.domain.dto.payment;

import com.zerobase.accommodation.domain.dto.order.AccommodationOrderItemDto;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccommodationCartPaymentDto {
    private Long cartId;

    private String customerName;

    private List<AccommodationOrderItemDto> orderItemList;

    private Integer totalPrice;
}
