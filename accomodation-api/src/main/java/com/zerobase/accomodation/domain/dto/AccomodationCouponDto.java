package com.zerobase.accomodation.domain.dto;

import com.zerobase.accomodation.domain.entity.coupon.AccomodationCoupon;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccomodationCouponDto {
    private Long id;
    private Long customerId;
    private Long couponGroupId;

    private boolean usedYN;

    private LocalDateTime usedTime;

    private LocalDateTime endTime;

    public static AccomodationCouponDto from(AccomodationCoupon accomodationCoupon) {
        return AccomodationCouponDto.builder()
            .id(accomodationCoupon.getId())
            .customerId(accomodationCoupon.getCustomerId())
            .couponGroupId(accomodationCoupon.getCouponGroupId())
            .usedYN(accomodationCoupon.isUsedYN())
            .usedTime(accomodationCoupon.getUsedTime())
            .endTime(accomodationCoupon.getEndTime())
            .build();
    }
}
