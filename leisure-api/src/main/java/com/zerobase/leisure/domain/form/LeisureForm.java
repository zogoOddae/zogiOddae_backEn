package com.zerobase.leisure.domain.form;

import java.time.LocalDate;
import lombok.Getter;

@Getter
public class LeisureForm {
	private String LeisureName;
	private String addr;
	private Integer price;
	private String pictureUrl;
	private String description;

	private Integer minPerson;
	private Integer maxPerson;

	private double lat;
	private double lon;
}
