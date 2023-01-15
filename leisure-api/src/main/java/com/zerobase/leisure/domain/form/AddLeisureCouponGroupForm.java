package com.zerobase.leisure.domain.form;

import com.zerobase.leisure.domain.type.CouponTarget;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class AddLeisureCouponGroupForm {
    private Long LeisureCouponGroupId;

    private Long salePrice;
    private CouponTarget couponTarget;
    private Integer issuedCount;

    private LocalDateTime endTime;
}
