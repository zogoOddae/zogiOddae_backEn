package com.zerobase.leisure.domain.dto.coupon;

import com.zerobase.leisure.domain.entity.coupon.LeisureCouponGroup;
import com.zerobase.leisure.domain.type.CouponTarget;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LeisureCouponGroupDto {
    private Long id;

    private Long salePrice;
    private CouponTarget couponTarget;
    private Integer issuedCount;

    private LocalDateTime endTime;

    public static LeisureCouponGroupDto from(LeisureCouponGroup leisureCouponGroup) {
        return LeisureCouponGroupDto.builder()
            .id(leisureCouponGroup.getId())
            .salePrice(leisureCouponGroup.getSalePrice())
            .couponTarget(leisureCouponGroup.getCouponTarget())
            .issuedCount(leisureCouponGroup.getIssuedCount())
            .endTime(leisureCouponGroup.getEndTime())
            .build();
    }
}
