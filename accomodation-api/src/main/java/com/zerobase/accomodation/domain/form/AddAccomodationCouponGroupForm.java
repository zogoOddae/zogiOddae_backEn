package com.zerobase.accomodation.domain.form;

import com.zerobase.accomodation.domain.type.CouponTarget;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class AddAccomodationCouponGroupForm {
    private Long accomodationCouponGroupid;

    private Long salePrice;
    private CouponTarget couponTarget;
    private Integer issusedcount;

    private LocalDateTime endTime;
}
