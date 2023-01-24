package com.zerobase.accommodation.domain.dto.accommodation;

import com.zerobase.accommodation.domain.entity.accommodation.AccommodationReview;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccommodationReviewDto {
	private Long reviewId;

	private Long customerId;
	private Long sellerId;
	private Long accommodationId;

	private double rating;
	private String description;

	private String reply;

	public static AccommodationReviewDto from(AccommodationReview accommodationReview) {
		return AccommodationReviewDto.builder()
			.reviewId(accommodationReview.getId())
			.sellerId(accommodationReview.getSellerId())
			.customerId(accommodationReview.getCustomerId())
			.accommodationId(accommodationReview.getAccommodationId())
			.rating(accommodationReview.getRating())
			.description(accommodationReview.getDescription())
			.reply(accommodationReview.getReply())
			.build();
	}
}
