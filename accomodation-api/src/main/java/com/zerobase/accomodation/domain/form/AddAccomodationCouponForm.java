package com.zerobase.accomodation.domain.form;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class AddAccomodationCouponForm {
    private Long id;
    private Long customerId;
    private Long couponGroupId;

    private boolean usedYN;

    private LocalDateTime usedTime;

    private LocalDateTime endTime;
}
