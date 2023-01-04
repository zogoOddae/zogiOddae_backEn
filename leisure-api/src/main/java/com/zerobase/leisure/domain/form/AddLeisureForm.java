package com.zerobase.leisure.domain.form;

import lombok.Getter;

@Getter
public class AddLeisureForm {
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
