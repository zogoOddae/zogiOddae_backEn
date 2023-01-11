package com.zerobase.accomodation.domain.form;

import lombok.Getter;

@Getter
public class AddAccomodationReviewForm {
    private Long customerId;
    private Long sellerId;
    private Long accomodationId;

    private double rating;
    private double description;

    private String reply;
}
