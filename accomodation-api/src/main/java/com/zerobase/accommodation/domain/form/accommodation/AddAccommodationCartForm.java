package com.zerobase.accommodation.domain.form.accommodation;

import java.time.LocalDate;
import lombok.Getter;

@Getter
public class AddAccommodationCartForm {
	private Long productId;
	private Integer persons;
	private LocalDate startAt;
	private LocalDate endAt;
}
