package com.zerobase.accomodation.domain.dto.accomodation;

import com.zerobase.accomodation.domain.entity.accomodation.AccomodationReview;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccomodationReviewDto {
	private Long accomodationReviewId;

	private Long customerId;
	private Long sellerId;
	private Long accomodationId;

	private double rating;
	private double description;

	private String reply;

	public static AccomodationReviewDto from(AccomodationReview accomodationReview) {
		return AccomodationReviewDto.builder()
			.accomodationReviewId(accomodationReview.getId())
			.sellerId(accomodationReview.getSellerId())
			.customerId(accomodationReview.getCustomerId())
			.accomodationId(accomodationReview.getAccomodationId())
			.rating(accomodationReview.getRating())
			.description(accomodationReview.getDescription())
			.reply(accomodationReview.getReply())
			.build();
	}
}
