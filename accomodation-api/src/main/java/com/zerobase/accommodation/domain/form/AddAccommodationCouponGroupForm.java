package com.zerobase.accommodation.domain.form;

import com.zerobase.accommodation.domain.type.CouponTarget;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class AddAccommodationCouponGroupForm {
    private Long accommodationCouponGroupid;

    private Long salePrice;
    private CouponTarget couponTarget;
    private Integer issusedcount;

    private LocalDateTime endTime;
}
