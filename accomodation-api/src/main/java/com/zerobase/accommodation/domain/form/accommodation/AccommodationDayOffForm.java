package com.zerobase.accommodation.domain.form.accommodation;

import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AccommodationDayOffForm {
    private LocalDate startAt;
    private LocalDate endAt;
}
