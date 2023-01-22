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

    private Long couponId;
    private Long couponGroupId;

    private Integer salePrice;

    private String endTime;

    public static AccommodationCouponDto from(AccommodationCoupon accommodationCoupon,Integer salePrice) {
        return AccommodationCouponDto.builder()
            .couponId(accommodationCoupon.getId())
            .salePrice(salePrice)
            .couponGroupId(accommodationCoupon.getCouponGroupId())
            .endTime(accommodationCoupon.getEndTime().toString())
            .build();
    }
}
