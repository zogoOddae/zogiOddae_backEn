package com.zerobase.leisure.domain.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LeisureForm {
	private String name;
	private String addr;
	private Integer price;
	private String pictureUrl;
	private String description;

	private String category;

	private Integer minPerson;
	private Integer maxPerson;

	private double lat;
	private double lon;
}
