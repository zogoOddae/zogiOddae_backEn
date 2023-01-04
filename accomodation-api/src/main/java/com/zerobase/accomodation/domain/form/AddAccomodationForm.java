package com.zerobase.accomodation.domain.form;

import lombok.Getter;

@Getter
public class AddAccomodationForm {
	private String name;
	private String addr;
	private Integer price;
	private String pictureUrl;
	private String description;

	private Integer minPerson;
	private Integer maxPerson;

	private double lat;
	private double lon;
}
