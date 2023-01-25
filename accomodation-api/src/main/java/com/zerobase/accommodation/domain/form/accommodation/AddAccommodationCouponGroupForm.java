package com.zerobase.accommodation.domain.form.accommodation;

import com.zerobase.accommodation.domain.type.CouponTarget;
import java.time.LocalDate;
import lombok.Getter;

@Getter
public class AddAccommodationCouponGroupForm {
    private Long couponGroupId;

    private Integer salePrice;
    private CouponTarget couponTarget;
    private Integer issuedCount;

    private LocalDate endTime;
}
