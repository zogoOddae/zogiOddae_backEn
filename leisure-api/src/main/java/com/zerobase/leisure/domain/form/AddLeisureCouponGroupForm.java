package com.zerobase.leisure.domain.form;

import com.zerobase.leisure.domain.type.CouponTarget;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class AddLeisureCouponGroupForm {
    private Long couponGroupId;

    private Integer salePrice;
    private CouponTarget couponTarget;
    private Integer issuedCount;

    private LocalDate endTime;
}
