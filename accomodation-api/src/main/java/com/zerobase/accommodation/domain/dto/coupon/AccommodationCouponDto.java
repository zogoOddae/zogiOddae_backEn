package com.zerobase.accommodation.domain.dto.coupon;

import com.zerobase.accommodation.domain.entity.coupon.AccommodationCoupon;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccommodationCouponDto {
    private Long id;
    private Long customerId;
    private Long couponGroupId;

    private boolean usedYN;

    private LocalDateTime usedTime;

    private LocalDate endTime;

    public static AccommodationCouponDto from(AccommodationCoupon accommodationCoupon) {
        return AccommodationCouponDto.builder()
            .id(accommodationCoupon.getId())
            .customerId(accommodationCoupon.getCustomerId())
            .couponGroupId(accommodationCoupon.getCouponGroupId())
            .usedYN(accommodationCoupon.isUsedYN())
            .usedTime(accommodationCoupon.getUsedTime())
            .endTime(accommodationCoupon.getEndTime())
            .build();
    }
}
