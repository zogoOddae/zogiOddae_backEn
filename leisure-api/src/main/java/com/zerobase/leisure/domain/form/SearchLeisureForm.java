package com.zerobase.leisure.domain.form;

import java.time.LocalDate;
import lombok.Getter;

@Getter
public class SearchLeisureForm {
	private String addr;
	private Integer personnel;
	private LocalDate startAt;
	private LocalDate endAt;
}
