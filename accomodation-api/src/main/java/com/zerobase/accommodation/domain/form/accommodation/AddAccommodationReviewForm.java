package com.zerobase.accommodation.domain.form.accommodation;

import lombok.Getter;

@Getter
public class AddAccommodationReviewForm {
    private Long customerId;
    private Long sellerId;
    private Long accommodationId;

    private double rating;
    private String description;

    private String reply;
}
