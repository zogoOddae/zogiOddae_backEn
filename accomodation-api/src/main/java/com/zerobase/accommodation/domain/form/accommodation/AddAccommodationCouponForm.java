package com.zerobase.accommodation.domain.form.accommodation;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class AddAccommodationCouponForm {
    private Long customerId;
    private Long couponGroupId;

    private boolean usedYN;

    private LocalDateTime usedTime;

    private LocalDate endTime;
}
