package com.zerobase.leisure.domain.form;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class AddLeisureCouponForm {
    private Long customerId;
    private Long couponGroupId;

    private boolean usedYN;

    private LocalDateTime usedTime;

    private LocalDateTime endTime;
}
