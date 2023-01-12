package com.zerobase.accomodation.domain.dto.coupon;

import com.zerobase.accomodation.domain.entity.coupon.AccomodationCouponGroup;
import com.zerobase.accomodation.domain.type.CouponTarget;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccomodationCouponGroupDto {
    private Long id;

    private Long salePrice;
    private CouponTarget couponTarget;
    private Integer issusedcount;

    private LocalDateTime endTime;

    public static AccomodationCouponGroupDto from(AccomodationCouponGroup accomodationCouponGroup) {
        return AccomodationCouponGroupDto.builder()
            .id(accomodationCouponGroup.getId())
            .salePrice(accomodationCouponGroup.getSalePrice())
            .couponTarget(accomodationCouponGroup.getCouponTarget())
            .issusedcount(accomodationCouponGroup.getIssusedcount())
            .endTime(accomodationCouponGroup.getEndTime())
            .build();
    }
}
