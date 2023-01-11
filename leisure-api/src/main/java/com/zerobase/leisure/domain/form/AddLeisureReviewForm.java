package com.zerobase.leisure.domain.form;

import lombok.Getter;

@Getter
public class AddLeisureReviewForm {
	private Long customerId;
	private Long sellerId;
	private Long leisureId;

	private double rating;
	private double description;

	private String reply;
}
