package com.zerobase.accommodation.domain.form;

import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AccommodationDayOffForm {
    private LocalDate startDate;
    private LocalDate endDate;
}
