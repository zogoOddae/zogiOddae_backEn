package com.zerobase.leisure.domain.form;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class AddLeisureCartForm {
	private Long leisureId;
	private Integer persons;
	private LocalDateTime startAt;
	private LocalDateTime endAt;
}
