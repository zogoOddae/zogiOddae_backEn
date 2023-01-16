package com.zerobase.accommodation.domain.dto.accommodation;

import com.zerobase.accommodation.domain.entity.accommodation.Accommodation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccommodationListDto {
	private Long id;
	private Long sellerId;

	private String accomodationName;
	private String addr;
	private Integer price;

	public static AccommodationListDto from(Accommodation accommodation) {
		return AccommodationListDto.builder()
			.id(accommodation.getId())
			.sellerId(accommodation.getSellerId())
			.accomodationName(accommodation.getAccommodationName())
			.addr(accommodation.getAddr())
			.price(accommodation.getPrice())
			.build();
	}
}
