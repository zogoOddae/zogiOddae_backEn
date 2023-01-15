package com.zerobase.leisure.domain.dto.coupon;

import com.zerobase.leisure.domain.entity.coupon.LeisureCoupon;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LeisureCouponDto {
    private Long id;
    private Long customerId;
    private Long couponGroupId;

    private boolean usedYN;

    private LocalDateTime usedTime;

    private LocalDateTime endTime;

    public static LeisureCouponDto from(LeisureCoupon leisureCoupon) {
        return LeisureCouponDto.builder()
            .id(leisureCoupon.getId())
            .customerId(leisureCoupon.getCustomerId())
            .couponGroupId(leisureCoupon.getCouponGroupId())
            .usedYN(leisureCoupon.isUsedYN())
            .usedTime(leisureCoupon.getUsedTime())
            .endTime(leisureCoupon.getEndTime())
            .build();
    }
}
