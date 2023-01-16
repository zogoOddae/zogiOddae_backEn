package com.zerobase.leisure.domain.form;

import java.time.LocalDate;
import lombok.Getter;

@Getter
public class LeisureDayOffForm {
	private LocalDate startDay;
	private LocalDate endDay;
}
