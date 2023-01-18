package com.zerobase.leisure.domain.form;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LeisureDayOffForm {
	private LocalDate startDay;
	private LocalDate endDay;
}
