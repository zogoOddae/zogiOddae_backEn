package com.zerobase.accommodation.domain.form;

import java.time.LocalDate;
import lombok.Getter;

@Getter
public class AccommodationForm {
	private String accommodationName;
	private String addr;
	private Integer price;
	private String pictureUrl;
	private String description;

	private Integer minPerson;
	private Integer maxPerson;

	private double lat;
	private double lon;

	private LocalDate dayOffStartDate;
	private LocalDate dayOffEndDate;
}
