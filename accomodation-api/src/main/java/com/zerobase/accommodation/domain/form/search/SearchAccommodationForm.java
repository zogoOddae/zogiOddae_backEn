package com.zerobase.accommodation.domain.form.search;

import java.time.LocalDate;
import lombok.Getter;

@Getter
public class SearchAccommodationForm {
    private String addr;
    private Integer personnel;
    private LocalDate startAt;
    private LocalDate endAt;
}
