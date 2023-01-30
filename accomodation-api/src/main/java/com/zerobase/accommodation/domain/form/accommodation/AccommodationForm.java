package com.zerobase.accommodation.domain.form.accommodation;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AccommodationForm {
	private String name;
	private String addr;
	private Integer price;
	private String pictureUrl;
	private String description;

	private Integer minPerson;
	private Integer maxPerson;

	private double lat;
	private double lon;

	private String checkIn;
	private String checkOut;

	private LocalDate dayOffStartDate;
	private LocalDate dayOffEndDate;

	private String category;
}
